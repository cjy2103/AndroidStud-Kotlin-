package com.example.calendar.room

import androidx.room.Entity

@Entity(tableName = "Schedule")
data class Schedule(
    val title: String,
    val date : String,
    val location: String,
    val description: String,
)