package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.Timer
import java.util.TimerTask

class MyService : Service() {

    private val TAG = "MyService"
    private val timer = Timer()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startLoggingTask()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun startLoggingTask() {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                // 주기적으로 실행할 작업을 여기에 작성
                Log.d(TAG, "Logging every 2 seconds")
            }
        }, 0, 2000)
    }
}