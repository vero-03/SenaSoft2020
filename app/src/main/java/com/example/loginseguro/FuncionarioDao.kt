package com.example.loginseguro

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FuncionarioDao {
    @Query("SELECT * FROM funcionarios")
    fun getAll(): LiveData<List<Funcionario>>

    @Query("SELECT * FROM funcionarios WHERE idFuncionaro = :id")
    fun get(id: Int): LiveData<Funcionario>



    @Insert
    fun insertAll(vararg funcionarios: Funcionario):List<Long>

    @Update
    fun update(funcionarios: Funcionario)

    @Delete
    fun delete(funcionarios: Funcionario)
}