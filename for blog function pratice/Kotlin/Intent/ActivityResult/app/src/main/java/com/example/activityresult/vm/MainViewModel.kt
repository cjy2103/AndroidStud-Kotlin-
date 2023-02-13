package com.example.activityresult.vm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.activityresult.SubActivity

class MainViewModel {

    private val str : MutableLiveData<String> = MutableLiveData()
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    fun getStr() = str

    fun init(context : Context){
        val activity = context as AppCompatActivity
        callback(activity)
        str.value = "단어오는 부분"
    }

    private fun callback(activity: AppCompatActivity) {
        resultLauncher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                if(it.data == null){
                    return@registerForActivityResult
                }
                val word = it.data?.getStringExtra("word")
                str.value = word
            }
        }
    }

    fun moveSub(view : View){
        val intent = Intent(view.context, SubActivity::class.java)
        resultLauncher.launch(intent)
    }


}