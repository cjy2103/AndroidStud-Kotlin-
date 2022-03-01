package com.example.fontchange

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fontchange.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initBinding()

        initFontSetting()
    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(){
        mBinding    = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 폰트세팅
     */
    private fun initFontSetting(){
        val tfMapleBold = Typeface.createFromAsset(this.assets, "Maplestory Bold.ttf")
        val tfMapleLight = Typeface.createFromAsset(this.assets, "Maplestory Light.ttf")

        binding.tvMapleBold.typeface = tfMapleBold
        binding.tvMapleLight.typeface = tfMapleLight
    }
}