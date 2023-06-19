package com.example.hilt.repository

import com.example.hilt.data.User

interface UserRepository {
    fun getUser() : User
}