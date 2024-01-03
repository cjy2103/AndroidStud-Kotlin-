package com.example.stepperindicator


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.stepperindicator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val circleCount = 3
        val circleSize = 100 // 이미지 원의 크기
        val circleGap = 20 // 원 간격

        var leftMargin = 0

        for (i in 0 until circleCount) {
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.circle_background) // circle_image.xml을 벡터 이미지로 생성하여 drawable 폴더에 추가해야 합니다.

            val params = ConstraintLayout.LayoutParams(circleSize, circleSize)
            params.leftMargin = leftMargin

            imageView.layoutParams = params
            binding.stepperLayout.addView(imageView)

            leftMargin += circleSize + circleGap
        }
    }
}