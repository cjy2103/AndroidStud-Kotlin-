package com.example.altbeacon

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.MonitorNotifier
import org.altbeacon.beacon.Region

class BeaconApplication : Application(), MonitorNotifier {
    private val TAG = "BeaconService"
    private lateinit var beaconManager: BeaconManager
    private val beaconRegion = Region("MyBeaconRegion", null, null, null)
    private val targetUuid = "비콘 UUID"
    private val targetMajor = "비콘 Major"
    private val targetMinor = "비콘 Minor"


    override fun onCreate() {
        super.onCreate()
        Log.v(TAG,"누가 먼저 시작?")
        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"))

        val builder = Notification.Builder(this,"My Notification Channel ID")
            .setSmallIcon(R.drawable.ic_beacon)
            .setContentTitle("Scanning for Beacons")
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, MainActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE flag

                )
            )

        val channel = NotificationChannel(
            "My Notification Channel ID",
            "My Notification Name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "My Notification Channel Description"
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        builder.setChannelId(channel.id)

        beaconManager.enableForegroundServiceScanning(builder.build(), 456)

        // MonitorNotifier 등록
        beaconManager.addMonitorNotifier(this)

        // 비콘 모니터링을 위한 Region 설정 및 시작
        val region = Region("MyBeaconRegion", null, null, null)
        beaconManager.startMonitoring(region)


        beaconSearch()
    }

    private fun beaconSearch() {

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
                        Log.d(TAG, "Target beacon found: $beaconUuid, $beaconMajor, $beaconMinor")
                        // 추가 작업 수행
                    }
                }
            } else {
                Log.v(TAG, "오류")
            }
        }
        beaconManager.startMonitoring(beaconRegion)
        beaconManager.startRangingBeacons(beaconRegion)
        beaconManager.setBackgroundModeInternal(true)
        beaconManager.setEnableScheduledScanJobs(false)
        beaconManager.backgroundBetweenScanPeriod = 0
        beaconManager.backgroundScanPeriod = 1100
    }


    override fun didEnterRegion(region: Region?) {
        Log.v(TAG, "지역진입")
    }

    override fun didExitRegion(region: Region?) {
        Log.v(TAG, "지역을 벗어났을때")
    }

    override fun didDetermineStateForRegion(state: Int, region: Region?) {
        Log.v(TAG, "지역에 대한 상태 판별")
    }
}
