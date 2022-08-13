package com.example.subscribecancel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subscribecancel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        btnAppSubscribe()
        btnAllSubscribe()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun btnAppSubscribe() {
        binding.btnAppSubscribe.setOnClickListener { v ->
            val uri =
                Uri.parse("https://play.google.com/store/account/subscriptions?sku=your-sub-product-id&package=your-app-package")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.android.vending")
            startActivity(intent)
        }
    }

    private fun btnAllSubscribe() {
        binding.btnAllSubscribe.setOnClickListener { v ->
            val uri =
                Uri.parse("https://play.google.com/store/account/subscriptions")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.android.vending")
            startActivity(intent)
        }
    }
}