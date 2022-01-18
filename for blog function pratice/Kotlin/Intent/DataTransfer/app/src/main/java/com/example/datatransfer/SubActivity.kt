package com.example.datatransfer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datatransfer.databinding.ActivitySubBinding
import java.util.*
import kotlin.properties.Delegates

class SubActivity : AppCompatActivity() {
    private var mBinding : ActivitySubBinding? = null
    private val binding get() = mBinding!!
    private var receiveStr = ""
    private var receiveInt = 0
    private var receiveBoolean = false
    private lateinit var receiveStrArr : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        initBinding()

        initialize()

        settingTextView()

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
     * @DESC: 초기화
     */
    private fun initialize(){
        if(intent.hasExtra("String") && intent.hasExtra("Integer") && intent.hasExtra("Boolean")
            && intent.hasExtra("StringArr")) {
            receiveStr = intent.getStringExtra("String").toString()
            receiveInt = intent.getIntExtra("Integer", 0)
            receiveBoolean = intent.getBooleanExtra("Boolean", false)
            receiveStrArr = intent.getStringArrayExtra("StringArr") as Array<String>
        }


    }

    /**
     * @DESC: 텍스트뷰 세팅
     */
    private fun settingTextView(){
        binding.tvString.text = receiveStr
        binding.tvInteger.text = receiveInt.toString()
        binding.tvBoolean.text = receiveBoolean.toString()
        binding.tvArray.text = receiveStrArr.contentToString()
    }

    /**
     * @DESC: 창닫기
     */
    private fun clickClose(){
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}