package com.example.loginseguro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_actividad__principal.*


class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad__principal)

        var listaFuncionarios = emptyList<Funcionario>()

        val database = AppDatabase.getDatabase(this)

        database.funcionarios().getAll().observe(this, Observer {
            listaFuncionarios = it

            val adapter = FuncionarioDatos(this, listaFuncionarios)

            listas.adapter = adapter
        })

        listas.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, FuncionarioInformacion::class.java)
            intent.putExtra("id", listaFuncionarios[position].idFuncionaro)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NuevoFuncionarioActivity::class.java)
            startActivity(intent)
        }

    }
}









/* val claseFuncionario = Funcionario(
            "Pepito",
            "Perez",
            "Sena",
            "Campoalegre",
            "1.077.332", R.drawable.kotlin
        )

        val claseFuncionario2 = Funcionario(
            "Andres",
            "Perez",
            "Sena",
            "Neiva",
            "12.047.332", R.drawable.kotlin
        )

        val claseFuncionario3 = Funcionario(
            "Veronica",
            "Perez",
            "Sena",
            "Neiva",
            "15.047.332", R.drawable.kotlin
        )

        val claseFuncionario4 = Funcionario(
            "Veronica",
            "Perez",
            "Sena",
            "Neiva",
            "15.047.332", R.drawable.kotlin
        )

        val claseFuncionario5 = Funcionario(
            "Veronica",
            "Perez",
            "Sena",
            "Neiva",
            "15.047.332", R.drawable.kotlin
        )



        val listaFuncionarios = listOf(claseFuncionario,claseFuncionario2,claseFuncionario3,claseFuncionario4,claseFuncionario5)
        val adapter = FuncionarioDatos(this,listaFuncionarios)
        listas.adapter = adapter

        listas.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this,FuncionarioInformacion::class.java)
            intent.putExtra("funcionario",listaFuncionarios[position])
            startActivity(intent)
        }*/