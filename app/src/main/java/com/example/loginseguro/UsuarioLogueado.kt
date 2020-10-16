package com.example.loginseguro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_usuario_logueado.*

class UsuarioLogueado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_logueado)


/*
* al momento de enviar datos entre componentes o vistas
* los recibo mediante la clase intent.extras
* creo la variale email de tipo string en donde le digo que puede ser nula
* luego obtengo el correo del usuario mediante la funcion getString()
*
* */

        val recibirDatos = intent.extras
        val email:String? = recibirDatos?.getString("email")
        recibir(email?:"")





        btnFuncionario.setOnClickListener {
            val intent = Intent(this,Principal::class.java)
            startActivity(intent)
        }
    }

    /*
    * traemos el id del texView  (txtEmail) de la vista  activity_usuario_logueado
    * le pasamos la variable email
    * luego creamos el boton cerrar en la vista activity_usuario_logueado
    * en donde llamamos la autenticacion  y la funcion signOut() que nos sirve
    * para cerrar el logueo del usuario
    * onBackPressed() regresa a la vista anterior es decir al login
    * */
    private fun recibir(email:String){

        txtEmail.text = email

        btnCerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

        }


    }
}