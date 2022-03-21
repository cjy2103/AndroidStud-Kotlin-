package com.example.thread

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.example.thread.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var handler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

//        settingTimerThread()
        settingTimerRunnable();
    }

    /**
     * @DESC: 뷰바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    
    /**
     * @DESC : Thread extends 방법
     */
    private fun settingTimerThread(){
        handler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val calendar = Calendar.getInstance()
                @SuppressLint("SimpleDateFormat") val simpleDateFormat =
                    SimpleDateFormat("HH:mm:ss")
                val time = simpleDateFormat.format(calendar.time)
                binding.tvTimer.text = time
            }
        }

        val newThread = NewThread()
        newThread.start()
    }

    /**
     * @DESC: Runnable implements 방법
     */
    private fun settingTimerRunnable(){
        handler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val calendar = Calendar.getInstance()
                @SuppressLint("SimpleDateFormat") val simpleDateFormat =
                    SimpleDateFormat("HH:mm:ss")
                val time = simpleDateFormat.format(calendar.time)
                binding.tvTimer.text = time
            }
        }

        val newRunnable = NewRunnable()
        val thread = Thread(newRunnable)
        thread.start()
    }

    /**
     * @DESC: Runnable implements
     */
    private inner class NewRunnable : Runnable {
        override fun run() {
            while (true) {
                try {
                    Thread.sleep(1000)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
                handler.sendEmptyMessage(0)
            }
        }
    }
    
    /**
     * @DESC: Thread extends
     */
    private inner class NewThread : Thread() {
        override fun run() {
            super.run()
            while (true) {
                try {
                    sleep(1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                handler.sendEmptyMessage(0)
            }
        }
    }
}