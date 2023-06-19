package com.example.hilt.repository

import com.example.hilt.data.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val name : String) : UserRepository{
    override fun getUser(): User {
        return User(name)
    }
}