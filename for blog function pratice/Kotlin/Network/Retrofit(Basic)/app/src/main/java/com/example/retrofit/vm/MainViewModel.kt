package com.example.retrofit.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.repository.MainRepository

class MainViewModel {

    private val mainRepository = MainRepository()

    private val _data : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = "데이터 들어오는 부분"
        }
    }

    val data : LiveData<String> get() = _data


    fun dataLoad(){

    }


}