package com.example.flow.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DATA")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0 ,
    val name : String ,
    val describe : String,
    val imagePath : String
)
