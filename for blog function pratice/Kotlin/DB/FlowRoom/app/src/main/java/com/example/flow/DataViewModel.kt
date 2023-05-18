package com.example.flow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.flow.room.Data
import com.example.flow.room.DataDao
import kotlinx.coroutines.flow.Flow

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dataDao : DataDao
    private val dataFlow : Flow<List<Data>>

    
}