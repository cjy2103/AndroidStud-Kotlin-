package com.example.calendar.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM SCHEDULE WHERE date = :date ")
    suspend fun loadByDate(date: String?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun 

}