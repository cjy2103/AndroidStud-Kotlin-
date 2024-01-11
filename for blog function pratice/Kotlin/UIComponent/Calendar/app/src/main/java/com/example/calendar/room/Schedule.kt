package com.example.calendar.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Schedule")
data class Schedule(
    @PrimaryKey
    val title: String,
    val date : String,
    val description: String,
)