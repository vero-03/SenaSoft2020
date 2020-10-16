package com.example.loginseguro

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "funcionarios")
class Funcionario(
    val nombre: String,
    val apellido: String,
    val entidad: String,
    val sede: String,
    val documento: String,
    val imagen: Int,

    @PrimaryKey(autoGenerate = true)
    var idFuncionaro: Int = 0
) :
    Serializable {


}