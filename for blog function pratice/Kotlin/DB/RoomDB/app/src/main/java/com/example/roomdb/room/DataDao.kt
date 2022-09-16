package com.example.roomdb.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {
    @Query("SELECT * FROM Data")
    suspend fun getAll() : List<Data>

    @Query("SELECT * FROM Data WHERE title = :title")
    suspend fun loadById(title: String?) : Data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: Data?)

    @Query("DELETE FROM Data")
    suspend fun deleteAll()
}