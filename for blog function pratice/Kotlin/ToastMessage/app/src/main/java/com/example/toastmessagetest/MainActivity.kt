package com.example.toastmessagetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.toastmessagetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        showToast()
    }

    /**
     * @DESC; 초기 바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 토스트 메시지 띄우기
     */
    private fun showToast(){
        binding.btnShow.setOnClickListener {
            Toast.makeText(this, "토스트 메시지 출력", Toast.LENGTH_SHORT).show()
        }
    }
}