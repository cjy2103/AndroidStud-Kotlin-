package com.example.camera.model

import android.content.ContentValues
import android.content.Context
import android.media.MediaActionSound
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.OutputFileOptions
import androidx.camera.core.ImageCapture.OutputFileResults
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.camera.activity.CameraActivity
import com.example.roomdb.util.LogUtils
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutionException

class CameraModel(private val cameraActivity: CameraActivity, private val context: Context) {

    private var cameraProviderListenableFuture : ListenableFuture<ProcessCameraProvider>? = null
    private var imageCapture : ImageCapture? = null
    private lateinit var cam : Camera
    private val sound : MediaActionSound

    init{
        cameraProviderListenableFuture = ProcessCameraProvider.getInstance(context)
        sound = MediaActionSound()
        cameraAddListener()
    }

    private fun cameraAddListener(){
        cameraProviderListenableFuture!!.addListener({
            try {
                val cameraProvider = cameraProviderListenableFuture!!.get()
                startCameraX(cameraProvider)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }, context.mainExecutor)
    }

    private fun startCameraX(cameraProvider: ProcessCameraProvider) {
        cameraProvider.unbindAll() // 열려있는 모든 카메라 닫기

//        val cameraSelector = CameraSelector.Builder()
//            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//            .build()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
            .build()

        val preview = Preview.Builder().build()

        cameraActivity.surfaceProvider(preview)

        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        cam = cameraProvider.bindToLifecycle(cameraActivity, cameraSelector, preview, imageCapture)
    }

    fun capturePhoto(){
        val timeStamp = System.currentTimeMillis()

        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timeStamp)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        imageCapture!!.takePicture(
            OutputFileOptions.Builder(
                context.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
            ).build(), context.mainExecutor, object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: OutputFileResults) {
                    Toast.makeText(context, "사진이 정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                    if (cam.cameraInfo.hasFlashUnit()) {
                        cam.cameraControl.enableTorch(true)
                    }
                    sound.play(MediaActionSound.SHUTTER_CLICK)
                    cameraActivity.runOnUiThread {
                        Handler(Looper.myLooper()!!).postDelayed({
                            cam.cameraControl.enableTorch(false)
                        }, 100)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context, "사진 저장에 실패했습니다 사유 : " +
                                exception.message, Toast.LENGTH_SHORT
                    ).show()
                    cam.cameraControl.enableTorch(false)
                }
            }
        )
    }

    fun destroy(){
        cameraProviderListenableFuture = null
        imageCapture = null
    }

}