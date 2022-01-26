package com.example.alertdialogexplain

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.alertdialogexplain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        initialize()
        
        clickAlertDialog()
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
     * @DESC: ALertDialog 열기
     */
    private fun clickAlertDialog(){
        binding.btnAlert.setOnClickListener{
            val builder = AlertDialog.Builder(mContext)

            builder.setTitle("AlertDialog 띄우기")
                .setMessage("이부분은 메시지를 입력하는 부분입니다.")
                .setPositiveButton("OK", )
        }
    }
}