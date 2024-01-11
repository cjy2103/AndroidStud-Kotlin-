package com.example.calendar.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Schedule::class], version = 1)
abstract class ScheduleDataBase : RoomDatabase() {
    abstract fun scheduleDao() : ScheduleDao

    companion object {
        private const val DATABASE_NAME = "schedule_db"

        @Volatile
        private var instance : ScheduleDataBase? = null

        fun getInstance(context: Context) : ScheduleDataBase {
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context).also{
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context): ScheduleDataBase {
            return Room.databaseBuilder(context, ScheduleDataBase::class.java, DATABASE_NAME)
                .build()
        }
    }
}