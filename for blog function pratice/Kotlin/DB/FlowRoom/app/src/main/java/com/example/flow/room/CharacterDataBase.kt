package com.example.flow.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Data::class], version = 1)
abstract class CharacterDataBase : RoomDatabase(){
    abstract fun dataDao() : DataDao

    companion object {
        private const val DATABASE_NAME = "character_db"

        @Volatile
        private var instance : CharacterDataBase? = null

        fun getInstance(context: Context): CharacterDataBase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CharacterDataBase {
            return Room.databaseBuilder(context, CharacterDataBase::class.java, DATABASE_NAME)
                .build()
        }
    }
}