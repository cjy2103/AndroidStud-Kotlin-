package com.example.flow.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow.R
import com.example.flow.data.Character
import com.example.flow.room.Data
import com.example.flow.room.DataDao
import com.example.flow.room.CharacterDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dataDao : DataDao
    private val allCharacter : Flow<List<Character>>

    private val _dataList = MutableStateFlow<List<Character>>(emptyList())
    val dataList : StateFlow<List<Character>> get() = _dataList

    init {
        val database = CharacterDataBase.getInstance(application)
        dataDao = database.dataDao()

        allCharacter = dataDao.getAllData().map { dataList ->
            dataList.map { convertDataToCharacter(it) }
        }

        viewModelScope.launch{
            allCharacter.collect { newDataList ->
                _dataList.value = newDataList
            }
        }


    }

    private fun convertDataToCharacter(data: Data): Character {
        return Character(
            title = data.name,
            describe = data.describe,
            imageKey = data.imagePath
        )
    }

    fun listAdd(){
        viewModelScope.launch {
            insertData(R.string.baknana, R.string.bak_describe)
            insertData(R.string.djmax, R.string.djmax_describe)
            insertData(R.string.djmax_falling_love, R.string.djmax_falling_love_describe)
            insertData(R.string.mwamwa, R.string.mwamwa_describe)
            insertData(R.string.tamtam, R.string.tamtam_describe)
        }
    }

    private suspend fun insertData(_title: Int, _describe: Int) {
        val title = getApplication<Application>().getString(_title)
        val describe = getApplication<Application>().getString(_describe)
        val key = getApplication<Application>().getString(_title)

        val character = Data(0,title, describe, key)
        dataDao.insertData(character)
    }

    fun deleteItem(){
        viewModelScope.launch {
            dataDao.deleteData()
        }
    }
    
}