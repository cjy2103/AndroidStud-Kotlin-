package com.example.myapplication.vm

import android.content.Context
import android.view.View
import android.widget.Toast

class MainViewModel {
    fun showToast(view : View){
        val context = view.context
        Toast.makeText(context, "버튼 클릭됨",Toast.LENGTH_SHORT).show()
    }
}