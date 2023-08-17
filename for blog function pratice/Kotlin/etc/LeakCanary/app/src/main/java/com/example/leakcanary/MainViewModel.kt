package com.example.leakcanary

import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private lateinit var context: Context

    fun init(context: Context){
        this.context = context
    }
}