package com.example.processbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.processbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        clickStart()
        clickReset()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun clickStart(){
        binding.btnStart.setOnClickListener {
            caseThread()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun caseThread(){
        binding.tvState.text = "Loading"
        val totalDuration = 3000 // 총 길이는 5초 (5000ms)
        val interval = 100 // 게이지를 업데이트할 간격 (100ms)
        val progressIncrement = 100 / (totalDuration / interval) // 각 간격마다 증가할 게이지 비율
        val maxProgress = 100

        val handler = Handler(Looper.myLooper()!!)
        var progress = 0

        // 일정 간격으로 게이지 업데이트
        val runnable: Runnable = object : Runnable {
            @SuppressLint("SetTextI18n")
            override fun run() {
                progress += progressIncrement
                binding.progressBar.progress = progress

                var percent = (progress.toDouble() / maxProgress * 100).toInt()
                if(percent > 100){
                    percent = 100
                }
                binding.tvPercent.text = "$percent%"

                if (progress < 100) {
                    handler.postDelayed(this, interval.toLong())
                } else {
                    binding.tvState.text = "Finish"
                }
            }
        }


// 애니메이션 시작
        handler.postDelayed(runnable, interval.toLong())
    }

    @SuppressLint("SetTextI18n")
    private fun clickReset(){
        binding.btnReset.setOnClickListener {
            binding.progressBar.progress = 0
            binding.tvState.text = "Wait"
            binding.tvPercent.text = "0%"
        }

    }
}