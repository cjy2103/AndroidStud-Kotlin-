package com.example.retrofit.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.repository.MainRepository
import com.example.retrofit.repository.RetrofitCallback
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    private val _data : MutableLiveData<String> by lazy {
        MutableLiveData<String>().apply {
            value = "데이터 들어오는 부분"
        }
    }

    val data : LiveData<String> get() = _data


    fun dataLoad(){
        viewModelScope.launch {
            mainRepository.callData(object : RetrofitCallback{
                override fun onSuccess(result: String) {
                    _data.value = result
                }

                override fun onError(throwable: Throwable) {
                    _data.value = "오류발생 $throwable"
                }

            })
        }
    }
}