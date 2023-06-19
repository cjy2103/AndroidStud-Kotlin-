package com.example.hilt.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hilt.data.User
import com.example.hilt.repository.UserRepository
import com.example.hilt.repository.UserRepositoryImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> get() = _user

    init {
        fetchUser()
    }

    private fun fetchUser(){
        _user.value = userRepository.getUser()
    }

    fun getUserName(){
        _user.value = User("영희")
    }
}