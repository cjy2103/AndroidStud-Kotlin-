package com.example.opennewintent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opennewintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mContext : Context
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initBinding()

        initialize()

        openSubActivity()
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
     * @DESC: SubActivity 열기
     */
    private fun openSubActivity(){
        binding.btnOpen.setOnClickListener {
            val intent = Intent(mContext, SubActivity::class.java)
            startActivity(intent)
        }
    }
}