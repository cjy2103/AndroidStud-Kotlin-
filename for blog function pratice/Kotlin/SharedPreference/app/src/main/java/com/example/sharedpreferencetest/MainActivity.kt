package com.example.sharedpreferencetest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferencetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initBinding()
        
        initialize()

        moveSubActivity()
        
        loadData();
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
        mActivity = this@MainActivity
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

    /**
     * @DESC: 데이터 로드
     */
    private fun loadData(){
        binding.btnLoad.setOnClickListener{
            val sharedPreferences = mActivity.getSharedPreferences("SaveData", MODE_PRIVATE)
            val str = sharedPreferences.getString("Data","저장된 데이터 없음")
            binding.tvWord.text = str
        }
    }
}