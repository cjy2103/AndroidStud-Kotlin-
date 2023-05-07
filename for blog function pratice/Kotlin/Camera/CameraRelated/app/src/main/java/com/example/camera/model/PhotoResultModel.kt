package com.example.camera.model

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File

class PhotoResultModel(private val context: Context) {

    fun deletePhoto(callback: PhotoCallback, imagePath: String?) {
        val contentResolver= context.contentResolver
        val projection = arrayOf(MediaStore.Images.Media.DATA)

        @SuppressLint("Recycle") val cursor =
            contentResolver.query(Uri.parse(imagePath), projection, null, null, null)

        if(cursor?.moveToFirst() == true){
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val filePath = cursor.getString(columnIndex)
            cursor.close()

            val file = File(filePath)

            if(file.delete()){
                callback.deleteSuccess()
            } else {
                callback.deleteFail()
            }
        }

    }
}