package com.example.flow.util

import com.example.flow.R

class ImagePath {

    companion object {
       fun imageSave(imagePath: String): String {
           var key = ""
           val imageTable = arrayOf(
               arrayOf(R.drawable.baknana, "박나나"),
               arrayOf(R.drawable.djmax_clear_fail, "클리어,페일"),
               arrayOf(R.drawable.djmax_falling_in_love, "FallingInLove"),
               arrayOf(R.drawable.mwama, "뫄뫄"),
               arrayOf(R.drawable.tamtam, "탬탬")
           )

           for (images in imageTable){
               if(imagePath == images[0]){
                   key = images[1] as String
               }
           }
           return key
       }

        fun imageLoad(key : String) : String {
            val imageUri = "drawable://"
            var imagePath = ""

            val imageTable = arrayOf(
                arrayOf(imageUri + R.drawable.baknana, "박나나"),
                arrayOf(imageUri + R.drawable.djmax_clear_fail, "클리어,페일"),
                arrayOf(imageUri + R.drawable.djmax_falling_in_love, "FallingInLove"),
                arrayOf(imageUri + R.drawable.mwama, "뫄뫄"),
                arrayOf(imageUri + R.drawable.tamtam, "탬탬")
            )

            for (images in imageTable){
                if(key == images[1]){
                    imagePath = images[0]
                }
            }

            return imagePath
        }
    }
}