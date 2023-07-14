package com.example.calculadoraroom.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Operation (
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo val operation: String = ""
)