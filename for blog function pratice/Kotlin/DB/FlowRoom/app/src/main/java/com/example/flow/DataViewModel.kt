package com.example.flow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.flow.room.Data
import com.example.flow.room.DataDao
import com.example.flow.room.RoomDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dataDao : DataDao
    private val dataFlow : Flow<List<Data>>

    private val _dataList = MutableStateFlow<List<Data>>(emptyList())
    val dataList: StateFlow<List<Data>> get() = _dataList

    init {
        val database = Room.databaseBuilder(
            application,
            RoomDataBase::class.java,
            "data_database"
        ).build()

        dataDao = database.dataDao()
        dataFlow = dataDao.getAllData()
        observeData()
    }

    private fun observeData() {
        viewModelScope.launch {
            dataFlow.collect { data ->
                _dataList.emit(data)
            }
        }
    }
    
}