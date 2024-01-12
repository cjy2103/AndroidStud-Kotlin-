package com.example.calendar.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ScheduleDao {
    @Query("SELECT * FROM SCHEDULE WHERE date = :date ")
    fun loadByDate(date: String) : Flow<Schedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)
    @Query("DELETE FROM SCHEDULE WHERE date = :date")
    suspend fun deleteByDate(date: String)

    @Query("UPDATE Schedule SET description = :description WHERE date = :date")
    suspend fun updateDesc(description : String, date: String)

}