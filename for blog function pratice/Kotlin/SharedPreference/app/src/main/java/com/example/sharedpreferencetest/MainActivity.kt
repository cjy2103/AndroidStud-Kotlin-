package com.example.sharedpreferencetest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferencetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initBinding()
        
        initialize()

        moveSubActivity()
    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 초기화
     */
    private fun initialize(){
        mContext = this@MainActivity
    }

    /**
     * @DESC: SubActivity 이동
     */
    private fun moveSubActivity(){
        binding.btnMove.setOnClickListener {
            val intent = Intent(mContext, SubActivity::class.java)
            startActivity(intent)
        }
    }
}