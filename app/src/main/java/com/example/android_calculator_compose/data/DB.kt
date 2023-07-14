package com.example.calculadoraroom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculadoraroom.data.dao.OperationsDao
import com.example.calculadoraroom.data.models.Operation

@Database(entities = [Operation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): OperationsDao
}