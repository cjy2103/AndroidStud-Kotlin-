package com.example.flow.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.flow.room.Data
import com.example.flow.room.DataDao
import com.example.flow.room.CharacterDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dataDao : DataDao
    private val allCharacter : Flow<List<Data>>

    private val _dataList = MutableStateFlow<List<Data>>(emptyList())
    val dataList: StateFlow<List<Data>> get() = _dataList

    init {
        val database = CharacterDataBase.getInstance(application)

        dataDao = database.dataDao()
        allCharacter = dataDao.getAllData()
    }

    


    private fun listAdd(){

    }

    private fun deleteItem(){

    }
    
}