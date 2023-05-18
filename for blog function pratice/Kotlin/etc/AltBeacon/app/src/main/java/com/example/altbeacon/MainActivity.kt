package com.example.altbeacon

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.altbeacon.databinding.ActivityMainBinding
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region


class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private val TAG = "BeaconSearch"
    private lateinit var beaconManager: BeaconManager
    private val beaconRegion = Region("MyBeaconRegion", null, null, null)
    private val targetUuid = "UUID ID 쓰세요"
    private val targetMajor = "Major ID 쓰세요"
    private val targetMinor = "Minor ID 쓰세요"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

//        init()
//        beaconSearch()
    }

    private fun viewBinding() {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init() {
        beaconManager = BeaconManager.getInstanceForApplication(this)
        // iBeacon 사용 지정
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"))
    }

    private fun beaconSearch(){
        binding.btnSearch.setOnClickListener {
            beaconManager.addRangeNotifier { beacons, _ ->
                if (beacons != null && beacons.isNotEmpty()) {
                    for (beacon in beacons) {
                        val beaconUuid = beacon.id1.toString()
                        val beaconMajor = beacon.id2.toString()
                        val beaconMinor = beacon.id3.toString()
                        Log.v(TAG, "값:$beacon")
                        Log.v(TAG, "비콘 UUID: $beaconUuid")
                        Log.v(TAG, "비콘 Major: $beaconMajor")
                        Log.v(TAG, "비콘 Minor: $beaconMinor")

                        if (beaconUuid == targetUuid && beaconMajor == targetMajor && beaconMinor == targetMinor) {
                            Log.d(
                                TAG,
                                "비콘 감지: $beaconUuid, $beaconMajor, $beaconMinor"
                            )
                        }
                    }
                }
            }

            beaconManager.startRangingBeacons(beaconRegion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        beaconManager.stopRangingBeacons(beaconRegion)
//        beaconManager.removeAllRangeNotifiers()

    }

}