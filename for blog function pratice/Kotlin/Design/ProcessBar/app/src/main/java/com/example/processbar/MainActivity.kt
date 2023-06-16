package com.example.processbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.processbar.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private var progressJob: Job? = null

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
//            caseThread()
            caseCoroutine()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun caseThread(){
        binding.tvState.text = "Loading"
        val totalDuration = 5000 // 총 길이는 5초 (5000ms)
        val interval = 100 // 게이지를 업데이트할 간격 (100ms)

        val maxProgress = 100

        val progressIncrement = 100.0 / (totalDuration.toDouble() / interval.toDouble())


        val handler = Handler(Looper.myLooper()!!)
        var progress = 0.0

        // 일정 간격으로 게이지 업데이트
        val runnable: Runnable = object : Runnable {
            @SuppressLint("SetTextI18n")
            override fun run() {
                progress += progressIncrement
                binding.progressBar.progress = progress.toInt()

                var percent = (progress / maxProgress * 100).toInt()
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
    private fun caseCoroutine(){
        binding.tvState.text = "Loading"
        val totalDuration = 5000 // 총 길이는 5초 (5000ms)
        val interval = 100 // 게이지를 업데이트할 간격 (100ms)
        val maxProgress = 100

        val progressIncrement = 100.0 / (totalDuration.toDouble() / interval.toDouble())

        progressJob?.cancel() // 기존 진행 중인 작업 취소

        progressJob = CoroutineScope(Dispatchers.Main).launch {
            var progress = 0.0

            while (progress < maxProgress) {
                delay(interval.toLong())

                progress += progressIncrement
                binding.progressBar.progress = progress.toInt()

                var percent = (progress / maxProgress * 100).toInt()
                if (percent > 100) {
                    percent = 100
                }
                binding.tvPercent.text = "$percent%"
            }

            binding.tvState.text = "Finish"
        }
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