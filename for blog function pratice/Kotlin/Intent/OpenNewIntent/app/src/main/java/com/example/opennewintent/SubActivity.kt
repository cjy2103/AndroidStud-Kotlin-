package com.example.opennewintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.opennewintent.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    
    private var mBinding : ActivitySubBinding? = null
    private val binding get() = mBinding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        
        initBinding()
        
        clickClose()
    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(){
        mBinding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 닫기 클릭
     */
    private fun clickClose(){
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}