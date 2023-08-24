package com.example.leakcanary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.leakcanary.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        moveSub()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun moveSub(){
        binding.button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }
    }
}