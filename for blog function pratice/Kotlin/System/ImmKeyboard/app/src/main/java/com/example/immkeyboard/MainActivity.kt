package com.example.immkeyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.immkeyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var imm : InputMethodManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        initalize()

        clickOpen()
        
        clickClose()
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
    private fun initalize(){
        binding.edtInput.requestFocus()
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    /**
     * @DESC: 키보드 열기
     */
    private fun clickOpen(){
        binding.btnOpen.setOnClickListener {
            imm.showSoftInput(binding.edtInput,0)
        }
    }

    /**
     * @DESC: 키보드 닫기
     */
    private fun clickClose(){
        binding.btnClose.setOnClickListener {
            imm.hideSoftInputFromWindow(binding.edtInput.windowToken,0)
        }
    }
}