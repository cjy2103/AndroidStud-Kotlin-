package com.example.sharedpreferencetest

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferencetest.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private var mBinding : ActivitySubBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mActivity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        
        initBinding()

        initialize()
        
        clickDataSend()
    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(){
        mBinding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 초기화
     */
    private fun initialize(){
        mActivity = this@SubActivity
    }

    /**
     * @DESC: 데이터 전송 버튼 클릭
     */
    private fun clickDataSend(){
        binding.btnDataSave.setOnClickListener{
            val sharedPreferences = mActivity.getSharedPreferences("SaveData", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("Data","데이터 저장")
            editor.apply()
            finish()
        }
    }
}