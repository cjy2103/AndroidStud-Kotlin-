package com.example.activityallclosekotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activityallclosekotlin.databinding.ActivitySubBinding
import kotlin.properties.Delegates

class SubActivity : AppCompatActivity() {

    private var mBinding : ActivitySubBinding? = null
    private val binding get() = mBinding!!

    private var number by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        initialize()

        openActivity()
        closeActivity()
        closeAllActivity()
    }

    private fun viewBinding(){
        mBinding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n")
    private fun initialize(){
        val intent = intent
        number = intent.getIntExtra("number",1)
        binding.tvCurrent.text = "현재 $number 번째 창"
    }

    private fun openActivity(){
        binding.btnOpen.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("number",number+1)
            startActivity(intent)
        }
    }

    private fun closeActivity(){
        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun closeAllActivity(){
        binding.btnAllClose.setOnClickListener {
            finishAffinity()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }




}