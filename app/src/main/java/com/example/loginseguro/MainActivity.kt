package com.example.loginseguro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        llamamos la funcion
        inicio()
//        btnIniciar.setOnClickListener {
//            val intent = Intent(this,UsuarioLogueado::class.java)
//            startActivity(intent)
//        }
    }

//    creamos una funcion privada
    private fun inicio(){

// llamamos el boton crear y la accion que va a realizar cuando le demos clic
    btnCrear.setOnClickListener {
//            verificamos que los datos del usuario no esten vacios
//        la verificacion la hacemos mediante la funcion isNotEmpty()

            if(email.text.isNotEmpty() && password.text.isNotEmpty()){
//
//
                    /*
                    * traemos la libreria de autenticacion de firebase y la funcion de crear usuario con contrasena
                    * addOnCompleteListener = cuando la actividad se anada o este completada entonces
                    * hacemos una condicion para validar de que el usuario fue creado
                    * si el usuario fue creado  entonces pasamos al la vista de UsuarioLogeado
                    * para pasar a la vista creamos una funcion llamada IrUserLogin()
                    * al no cumplirse la condicion genera una alerta
                    * creamos una funcion para generar la alerta()
                    *
                    * */

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email.text.toString()
                    ,password.text.toString()).addOnCompleteListener{

                        if(it.isSuccessful){
// para evitar el error de variables nulas hacemos uso de  ?
                            IrUserLogin(it.result?.user?.email?:"")
                        } else {

                            alerta()
                        }


                    }



            }


        }

    /*
                   * traemos la libreria de autenticacion de firebase y la funcion de logear el usuario con contrasena
                   * addOnCompleteListener = cuando la actividad se anada o este completada entonces
                   * hacemos una condicion para validar de que el usuario fue creado
                   * si el usuario fue creado  entonces pasamos al la vista de UsuarioLogeado
                   * para pasar a la vista creamos una funcion llamada IrUserLogin()
                   * al no cumplirse la condicion genera una alerta
                   * creamos una funcion para generar la alerta()
                   *
                   * */

        btnIniciar.setOnClickListener {

            if(email.text.isNotEmpty() && password.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email.text.toString()
                        ,password.text.toString()).addOnCompleteListener{

                        if(it.isSuccessful){

                            IrUserLogin(it.result?.user?.email?:"")
                        } else {

                            alerta()
                        }


                    }



            }


        }






    }

/*
la funcion IrUserLogin tiene como parametro email que es de tipo String
* Intent es una funcion que nos sirve para navegar entre vistas o componente
* tambien nos sirve para enviar datos entre los componentes o vistas
* para enviarlos creamos el objeto apply  y los datos los anadimos mediante putExtra
*
* */
    private fun IrUserLogin(email:String){

        val intent = Intent(this,UsuarioLogueado::class.java).apply {
            putExtra("email",email)
        }
        startActivity(intent)

    }

/*
* creamos una funcion alerta
* hacemos uso de la clase AlertDialog y la funcion Builder que nos sirve para asignarle
* un mensaje a nuestra alerta
* setTitle = titulo de la alerta
* setMessage = mensaje que lleva la alerta -> poner lo que ustedes quieran
* setPositiveButtton = sirve para que el usuario pueda cerrar
* el boton se llama Aceptar
* para mostar la alerta hacemos uso de la funcion show()
* */

    private fun alerta(){

        val mensajeAlerta = AlertDialog.Builder(this)
        mensajeAlerta.setTitle("Error")
        mensajeAlerta.setMessage("se ha producido un error de autenticacion")
        mensajeAlerta.setPositiveButton("Aceptar",null)
        val dialogo: AlertDialog = mensajeAlerta.create()
        dialogo.show()


    }



}