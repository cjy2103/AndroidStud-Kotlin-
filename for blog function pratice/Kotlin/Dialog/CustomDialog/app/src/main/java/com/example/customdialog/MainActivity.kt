package com.example.customdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customdialog.databinding.ActivityMainBinding
import com.example.customdialog.dialog.CustomDialog

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        dialogShow()

    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 다이얼로그 열기
     */
    private fun dialogShow(){
        binding.btnDialog.setOnClickListener{
            val fm = supportFragmentManager

            val dialogFragment = CustomDialog()
            dialogFragment.show(fm,"CustomDialog")
        }
    }
}