package com.example.roomdb.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Data {
    @PrimaryKey
    @NonNull
    internal lateinit var title: String

    @ColumnInfo
    internal lateinit var msg: String

}