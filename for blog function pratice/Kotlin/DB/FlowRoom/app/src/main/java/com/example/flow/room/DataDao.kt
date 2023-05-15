package com.example.flow.room

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {
    @Query("SELECT * FROM DATA")
    fun getAllData() : Flow<List<Data>>


}