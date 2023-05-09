package com.example.ble

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ble.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val deviceAddress = "00:00:00:00:00:00" // BLE 장치의 MAC 주소 (수정 하세요)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        blePairing()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
    }

    private fun blePairing(){
        binding.btnPairing.setOnClickListener {
            pairingDevice()
        }
    }

    private fun pairingDevice(){
        val device = bluetoothAdapter.getRemoteDevice(deviceAddress)
        try{
            val method = device.javaClass.getMethod("createBond")
            method.invoke(device)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}