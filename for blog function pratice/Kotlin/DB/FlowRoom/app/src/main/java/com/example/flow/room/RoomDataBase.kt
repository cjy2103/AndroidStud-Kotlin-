package com.example.flow.room

import androidx.room.Database

@Database(entities = [Data::class], version = 1)
abstract class RoomDataBase {
    abstract fun dataDao() : DataDao
}