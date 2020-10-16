package com.example.loginseguro

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import kotlinx.android.synthetic.main.activity_funcionario_informacion.*
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FuncionarioInformacion : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var funcionario: Funcionario
    private lateinit var funcionarioLiveData: LiveData<Funcionario>
    private val EDIT_ACTIVITY = 49

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funcionario_informacion)

        database = AppDatabase.getDatabase(this)

        val idFuncionario = intent.getIntExtra("id", 0)
        val imageUri = ImageController.getImageUri(this, idFuncionario.toLong())
        imagen2.setImageURI(imageUri)

        funcionarioLiveData = database.funcionarios().get(idFuncionario)

      funcionarioLiveData.observe(this, Observer {
            funcionario = it

          nombre.text = funcionario.nombre
          apellido.text = funcionario.apellido
          entidad.text = funcionario.entidad
          sede.text = funcionario.sede
          documento.text = funcionario.documento
//          imagen2.setImageResource(funcionario.imagen)



    })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.funcionario_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_Item -> {
                val intent = Intent(this, NuevoFuncionarioActivity::class.java)
                intent.putExtra("funcionario", funcionario)
                startActivityForResult(intent, EDIT_ACTIVITY)
            }

            R.id.delet_Item -> {
                funcionarioLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.funcionarios().delete(funcionario)
                    ImageController.deleteImage(this@FuncionarioInformacion, funcionario.idFuncionaro.toLong())
                    this@FuncionarioInformacion.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == EDIT_ACTIVITY && resultCode == Activity.RESULT_OK -> {
                imagen2.setImageURI(data!!.data)
            }
        }
    }

}




















/*
//    Intent = vinculacion entre componentes separados
//        Vincula informacion
//        Intent -> Intension hacer una tarea
// serializable -> traer un conjunto de datos con su tipo

        val funcionario = intent.getSerializableExtra("funcionario") as Funcionario
//        nombre , apellido, entidad , sede , documento , imagen  provienen del nombre del Id de cada
//        TextView

        nombre.text = funcionario.nombre
        apellido.text = funcionario.apellido
        entidad.text = funcionario.entidad
        sede.text = funcionario.sede
        documento.text = funcionario.documento
        imagen2.setImageResource(funcionario.imagen)

//        btnVer.setOnClickListener {
//
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//
//        }*/