package com.example.loginseguro

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import kotlinx.android.synthetic.main.activity_nuevo_funcionario.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoFuncionarioActivity : AppCompatActivity() {


    private val SELECT_ACTIVITY = 50
       private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_funcionario)

        var idFuncionario: Int? = null

        if (intent.hasExtra("funcionario")) {
            val funcionario = intent.extras?.getSerializable("funcionario") as Funcionario

            nombre_et.setText(funcionario.nombre)
            apellido_et.setText(funcionario.apellido)
            entidad_et.setText(funcionario.entidad)
            sede_et.setText(funcionario.sede)
            documento_et.setText(funcionario.documento.toString())
            idFuncionario = funcionario.idFuncionaro

            val imageUri = ImageController.getImageUri(this, idFuncionario.toLong())
            imageSelect_iv.setImageURI(imageUri)
        }

        val database = AppDatabase.getDatabase(this)

        btn_save.setOnClickListener {

            val nombre = nombre_et.text.toString()
            val apellido = apellido_et.text.toString()
            val entidad = entidad_et.text.toString()
            val sede = sede_et.text.toString()
            val documento =  documento_et.text.toString()






            val funcionario = Funcionario(nombre, apellido, entidad,sede,documento, R.drawable.ic_launcher_background)

            if (idFuncionario != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    funcionario.idFuncionaro = idFuncionario

                    database.funcionarios().update(funcionario)

                    imageUri?.let {
                        val intent = Intent()
                        intent.data = it
                        setResult(Activity.RESULT_OK, intent)
                        ImageController.saveImage(this@NuevoFuncionarioActivity, idFuncionario.toLong(), it)
                    }

                    this@NuevoFuncionarioActivity.finish()
                }
            } else if(nombre.isNotEmpty() && apellido.isNotEmpty() && entidad.isNotEmpty() && sede.isNotEmpty() && documento.isNotEmpty())
            {
                CoroutineScope(Dispatchers.IO).launch {
                    val id = database.funcionarios().insertAll(funcionario)[0]
                    imageUri?.let {
                        ImageController.saveImage(this@NuevoFuncionarioActivity, id , it)
                    }
                    this@NuevoFuncionarioActivity.finish()
                }
            }
        }

        imageSelect_iv.setOnClickListener {
            ImageController.selectPhotoFromGallery(this, SELECT_ACTIVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data

                imageSelect_iv.setImageURI(imageUri)
            }
        }
    }
}