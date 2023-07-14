package com.example.calculadoraroom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.calculadoraroom.data.models.Operation

@Dao
interface OperationsDao {
    @Query("SELECT * FROM operation")
    fun getAll(): List<Operation>

    @Insert
    fun insertAll(vararg questions: Operation)

    @Delete
    fun delete(question: Operation)
}