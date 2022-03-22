package com.example.runonuithread

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.runonuithread.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var runnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        timerSetting()
    }

    /**
     * @DESC: 뷰바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 시간세팅
     */
    @SuppressLint("SimpleDateFormat")
    private fun timerSetting(){
        runnable = Runnable {
            val calendar = Calendar.getInstance()

            val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
            val strTime = simpleDateFormat.format(calendar.time)

            binding.tvTime.text = strTime
        }

        val newRunnable = NewRunnable()
        val thread = Thread(newRunnable)
        thread.start()
    }

    private inner class NewRunnable : Runnable{
        override fun run() {
            while (true){
                Thread.sleep(1000)

                runOnUiThread(runnable)
            }
        }

    }
}