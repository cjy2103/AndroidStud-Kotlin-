package com.example.roomdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null
        private const val DATABASE_NAME = "ROOM"

        fun getInstance(context: Context): RoomDB? {
            if (INSTANCE == null) {
                synchronized(RoomDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RoomDB::class.java, DATABASE_NAME
                        ).build()
                    }
                    return INSTANCE
                }
            }
            return INSTANCE
        }
    }

}