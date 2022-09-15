package com.example.ndk

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ndk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var str : String
//    private external fun getNdk(): String?
//
//    init {
//        try{
//            System.loadLibrary("test");
//        }
//        catch (ule : UnsatisfiedLinkError){
//            Log.v("NDKTest","NDK 오류발생");
//            ule.printStackTrace();
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        initialize()
        clickNdkTest()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize(){
//        str = getNdk()!!
    }

    private fun clickNdkTest(){
        binding.btnClick.setOnClickListener {
            binding.tvNdk.text = str
        }
    }
}