package com.example.datatransfer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datatransfer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mContext : Context
    private lateinit var strArr : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        initialize()

        moveSubActivity()
    }

    /**
     * @DESC: 초기바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC : 초기화
     */
    private fun initialize(){
        mContext = this@MainActivity
        strArr = arrayOf("사과","바나나","귤")
    }

    /**
     * @DESC: 데이터 전달
     */
    private fun moveSubActivity(){
        binding.btnMove.setOnClickListener {
            val intent = Intent(mContext,SubActivity::class.java)
            intent.putExtra("String","문자열전송")
            intent.putExtra("Integer",5)
            intent.putExtra("Boolean",true)
            intent.putExtra("StringArr",strArr)
            startActivity(intent)
        }
    }


}