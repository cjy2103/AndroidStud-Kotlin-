package com.example.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ble.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private val TAG = "BLE Example"

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val deviceAddress = "00:00:00:00:00:00" // BLE 장치의 MAC 주소 (수정 하세요)

    private lateinit var socket : BluetoothSocket
    private var outputStream: OutputStream? = null
    private var inputStream: InputStream? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        blePairing()

        bleSocketCreate()

        bleSocketClose()
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


    private fun bleSocketCreate(){
        binding.btnSocketCreate.setOnClickListener {
            createSocket()
        }
    }

    @SuppressLint("MissingPermission")
    private fun createSocket(){
        val pairedDevices = bluetoothAdapter.bondedDevices
        var deviceToConnect: BluetoothDevice? = null

        for(device in pairedDevices){
            if(device.address == deviceAddress){
                deviceToConnect = device
                break
            }
        }

        if(deviceToConnect == null) {
            Log.e(TAG, "Device not found.")
            return
        }

        // BluetoothSocket을 생성하고, 장치와 연결합니다.
        val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
        val socket = deviceToConnect.createRfcommSocketToServiceRecord(uuid)
        try {
            socket.connect()
            Log.i(TAG, "Socket connected.")
            outputStream = socket.outputStream
            inputStream = socket.inputStream
        } catch (e: IOException) {
            Log.e(TAG, "Error occurred while connecting to socket.", e)
            return
        }
    }

    private fun bleSocketClose(){
        binding.btnSocketClose.setOnClickListener {
            socket.close()
        }
    }

}