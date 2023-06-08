package com.example.flow.util

import com.example.flow.R

class ImagePath {

    companion object {
        fun imageLoad(key : String) : Int {
            var imagePath = 0

            val imageTable = arrayOf(
                arrayOf(R.drawable.baknana, "박나나"),
                arrayOf(R.drawable.djmax_clear_fail, "클리어,페일"),
                arrayOf(R.drawable.djmax_falling_in_love, "FallingInLove"),
                arrayOf(R.drawable.mwama, "뫄뫄"),
                arrayOf(R.drawable.tamtam, "탬탬버린")
            )

            for (images in imageTable){
                if(key == images[1]){
                    imagePath = images[0] as Int
                    break
                }
            }

            return imagePath
        }
    }
}