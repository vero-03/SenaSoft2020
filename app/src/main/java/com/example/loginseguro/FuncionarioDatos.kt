package com.example.loginseguro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.atributos_funcionario.view.*

class FuncionarioDatos(
    private val mContext: Context,
    private val listaFuncionarios: List<Funcionario>
) : ArrayAdapter<Funcionario>(mContext, 0, listaFuncionarios) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val lienzo = LayoutInflater.from(mContext).inflate(R.layout.atributos_funcionario,parent,false)
        val funcionario = listaFuncionarios[position]
        lienzo.nombre.text = funcionario.nombre
        lienzo.apellido.text = funcionario.apellido
//        lienzo.entidad.text = funcionario.entidad
//        lienzo.sede.text = funcionario.sede
//        lienzo.documento.text = funcionario.documento
        lienzo.imagen.setImageResource(funcionario.imagen)
        val imageUri = ImageController.getImageUri(mContext, funcionario.idFuncionaro.toLong())

        lienzo.imagen.setImageURI(imageUri)

        return lienzo
    }

}

