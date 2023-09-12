package com.example.retrofit.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel {
    private val _data : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = "데이터 들어오는 부분"
        }
    }

    val data : LiveData<String> get() = _data

    


}