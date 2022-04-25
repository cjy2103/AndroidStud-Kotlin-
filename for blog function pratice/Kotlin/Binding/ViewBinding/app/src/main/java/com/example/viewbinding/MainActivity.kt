package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null // 바인딩 객체 선언
    private val binding get() = mBinding!! // null 체크 편의성 위해 바인딩 변수 재선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding()

        wordChagne()

    }

    /**
     * @DESC:초기 바인딩
     */
    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 단어변경
     */
    private fun wordChagne(){
        binding.btnChagne.setOnClickListener {
            binding.tvWord.text = "단어변경"
        }
    }
}