package com.example.flow.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class RoomDataBase : RoomDatabase(){
    abstract fun dataDao() : DataDao
}