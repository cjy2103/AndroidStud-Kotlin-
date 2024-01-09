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


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var thermalList: ArrayList<ThermalInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInfo.setOnClickListener {
            loadThermal()

            Log.v("저장된 개수: ", "${thermalList.size}")

            for(i in 0 until thermalList.size){
                Log.v("이름:", "${thermalList[i].thermalName}")
                Log.v("값:", thermalList[i].thermalValue)
            }

            val thermals = thermalList.map { it.thermalName to it.thermalValue }
            binding.tvInfo.text = thermals.joinToString(", ")

        }
    }

    private fun test(){
        val thermalDirectory = File("/sys/devices/virtual/thermal/cooling_device13")

        // 경로가 폴더인지 확인

        // 경로가 폴더인지 확인
        if (thermalDirectory.isDirectory) {
            // 폴더 내의 모든 파일과 폴더 목록을 가져오기
            val files = thermalDirectory.list()

            // 파일 및 폴더 목록 출력
            for (fileName in files) {
                Log.d("ThermalInfo", "File or Folder: $fileName")
            }
        } else {
            Log.e("ThermalInfo", "/sys/devices/virtual/thermal/ is not a directory")
        }
    }

    private fun getBatteryTemperature(): Float {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus: Intent? = registerReceiver(null, intentFilter)
        val temperature = batteryStatus?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) ?: 0
        return temperature / 10f // 배터리 온도는 0.1도 단위로 제공됩니다.
    }

    private fun loadThermal() {
        thermalList = ArrayList()
        val dir = File("/sys/devices/virtual/thermal")
        val files = dir.listFiles()
        for (file in files!!) {
            try {
                val tempFileValue = File(file.absolutePath + "/temp")
                val tempFileName = File(file.absolutePath + "/type")
                val bufferedReaderValue = BufferedReader(FileReader(tempFileValue))
                val bufferedReaderName = BufferedReader(FileReader(tempFileName))
                val lineName = bufferedReaderName.readLine()
                val lineValue = bufferedReaderValue.readLine()

                thermalList.add(ThermalInfo(lineName, lineValue))

                bufferedReaderName.close()
                bufferedReaderValue.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

}