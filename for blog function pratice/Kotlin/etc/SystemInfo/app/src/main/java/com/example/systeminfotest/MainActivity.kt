package com.example.systeminfotest

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.systeminfotest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.RandomAccessFile
import java.util.Arrays


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        for (sensor in sensorList) {
            Log.d("Sensor", "Name: ${sensor.name}, Type: ${sensor.type}")
        }

        binding.btnInfo.setOnClickListener {


            val batteryTemperature = getBatteryTemperature()
            // 배터리 온도 사용
            // 예: 로그에 출력
            println("배터리 온도: $batteryTemperature")

//            val temp = getCpuTemperature()
//            println(temp)
//            val hardware =
//                (getSystemService(HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager)
//                    .getDeviceTemperatures(HardwarePropertiesManager.DEVICE_TEMPERATURE_CPU, HardwarePropertiesManager.TEMPERATURE_CURRENT)
//
//            println(hardware)

            val reader = RandomAccessFile("sys/class/thermal/thermal_zone1/temp", "r")
            val line = reader.readLine()

            println("test: $line")

            val cpuTemp = line.toFloat()/1000.0f

            binding.tvInfo.text = cpuTemp.toString()

            loadThermal()


            Log.v("ㅎㅇㅎㅇㅎㅇ", arrayOf(thermalList[0]).contentToString())
            // 되는거
            ///sys/class/thermal/thermal_zone1/temp 27도


        }
    }

    private fun getBatteryTemperature(): Float {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus: Intent? = registerReceiver(null, intentFilter)
        val temperature = batteryStatus?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) ?: 0
        return temperature / 10f // 배터리 온도는 0.1도 단위로 제공됩니다.
    }

    fun getCpuTemperature(): Float {
        val process: Process
        return try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp")
            process.waitFor()
            val bufferedReader =
                BufferedReader(InputStreamReader(process.inputStream))
            val line = bufferedReader.readLine()
            line.toFloat() / 1000.0f
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            0.0f
        }
    }

    private fun getInfo(){
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
        }
    }

    private lateinit var thermalList: ArrayList<ThermalInfo>
    private lateinit var thermalList2: ArrayList<ThermalInfo>
    private fun loadThermal() {
        thermalList = ArrayList()
        val dir = File("/sys/devices/virtual/thermal/")
        val files = dir.listFiles()
        for (file in files!!) {
            try {
                val tempFileValue = File(file.absolutePath + "/temp")
                val tempFileName = File(file.absolutePath + "/type")
                val bufferedReaderValue = BufferedReader(FileReader(tempFileValue))
                val bufferedReaderName = BufferedReader(FileReader(tempFileName))
                val lineName = bufferedReaderName.readLine()
                val lineValue = bufferedReaderValue.readLine()
                if (lineValue.trim { it <= ' ' } != "0") {
                    thermalList.add(ThermalInfo(lineName, GetDetail.GetDetails.getFormattedTemp(lineValue)))
                }
                bufferedReaderName.close()
                bufferedReaderValue.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

}