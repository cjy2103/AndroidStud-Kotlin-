package com.example.systeminfotest

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.systeminfotest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor

    private val temperatureListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                val cpuTemperature = event.values[0]
                // CPU 온도 데이터 사용
                // 예: 로그에 출력
                println("CPU 온도: $cpuTemperature")
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // 정확도 변경 시 처리 로직 (생략 가능)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnInfo.setOnClickListener {

            getInfo()


        }
    }

    fun getInfo(){
        val process: Process

        try {
            process =
                Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp")
            process.waitFor()
            val bufferedReader =
                BufferedReader(InputStreamReader(process.inputStream))
            val line = bufferedReader.readLine()
            line.toFloat() / 1000.0f
            println("CPU 온도: $line")

        } catch (e: Exception) {
            e.printStackTrace()
            0.0f
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // 액티비티가 종료될 때 센서 해제
        sensorManager.unregisterListener(temperatureListener)
    }


    private val temperatureSensor: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // TODO Auto-generated method stub
        }

        override fun onSensorChanged(event: SensorEvent) {
            // TODO Auto-generated method stub
            val temp = event.values[0]
            Log.i("sensor", "sensor temp = $temp")
        }
    }
}