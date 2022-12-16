package com.example.camera.model

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.VideoCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.camera.activity.VideoRecordActivity
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutionException

class VideoRecordModel(private val videoRecordActivity: VideoRecordActivity,
                       private val context: Context) {

    private val cameraProviderListenableFuture: ListenableFuture<ProcessCameraProvider> =
        ProcessCameraProvider.getInstance(context)

    private lateinit var imageCapture: ImageCapture

    private lateinit var videoCapture: VideoCapture


    init{
        cameraAddListener()
    }

    private fun cameraAddListener(){
        cameraProviderListenableFuture.addListener({
            try {
                val cameraProvider = cameraProviderListenableFuture.get()
                startCameraX(cameraProvider)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }, context.mainExecutor)
    }

    @SuppressLint("RestrictedApi")
    private fun startCameraX(cameraProvider: ProcessCameraProvider) {
        cameraProvider.unbindAll()

        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        val preview = Preview.Builder().build()

        videoRecordActivity.surfaceProvider(preview)

        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        videoCapture = VideoCapture.Builder()
            .setVideoFrameRate(30)
            .build()

        cameraProvider.bindToLifecycle(videoRecordActivity, cameraSelector, preview
            ,imageCapture, videoCapture)

    }



    @SuppressLint("MissingPermission", "RestrictedApi")
    fun videoStart(){
        val timestamp = System.currentTimeMillis()
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE,"video/mp4")

        videoCapture.startRecording(VideoCapture.OutputFileOptions.Builder(
              context.contentResolver
            , MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            , contentValues
        ).build()
        , context.mainExecutor
        , object : VideoCapture.OnVideoSavedCallback {
                override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                    Toast.makeText(context, "동영상이 정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                    Toast.makeText(
                        context, "사진 저장에 실패했습니다 사유 : " +
                                message, Toast.LENGTH_SHORT
                    ).show()
                }
        }

        )

    }

    @SuppressLint("RestrictedApi")
    fun stop(){
        videoCapture.stopRecording()
    }
}