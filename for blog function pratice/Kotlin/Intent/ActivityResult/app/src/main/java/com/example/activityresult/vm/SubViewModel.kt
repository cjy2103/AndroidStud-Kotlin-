package com.example.activityresult.vm


import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField

class SubViewModel {

    private var word = ObservableField("")

    fun getWord() = word

    fun sendData(view : View){
        val activity = view.context as AppCompatActivity
        val intent = Intent()
        intent.putExtra("word",word.get())
        activity.setResult(Activity.RESULT_OK, intent)
        activity.finish()
    }

}