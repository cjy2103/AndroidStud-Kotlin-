package com.example.camera.model

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.camera.activity.CameraActivity
import com.example.camera.activity.MainActivity
import com.example.camera.activity.VideoRecordActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainModel(private val mainActivity: MainActivity, private val context: Context) {

    private var permissionlistener : PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
//                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
            Toast.makeText(
                context, "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT
            ).show()
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkPermission(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        checkPermission(Manifest.permission.CAMERA)
        checkPermission(Manifest.permission.RECORD_AUDIO)
    }

    private fun checkPermission(permission : String){
        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setDeniedMessage(
                """
                If you reject permission,you can not use this service
                
                Please turn on permissions at [Setting] > [Permission]
                """.trimIndent()
            ).setPermissions(permission)
            .check()
    }

    fun cameraPerMissionCheck(){
        val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        val storage =
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (camera != PackageManager.PERMISSION_GRANTED
            && storage != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "저장소, 카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(context, CameraActivity::class.java)
            mainActivity.startActivity(intent)
        }
    }

    fun videoPerMissionCheck(){
        val audio = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
        val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        val storage =
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (camera != PackageManager.PERMISSION_GRANTED &&
            storage != PackageManager.PERMISSION_GRANTED &&
            audio != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context,"카메라, 저장소, 오디오 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(context, VideoRecordActivity::class.java)
            mainActivity.startActivity(intent)
        }
    }


}