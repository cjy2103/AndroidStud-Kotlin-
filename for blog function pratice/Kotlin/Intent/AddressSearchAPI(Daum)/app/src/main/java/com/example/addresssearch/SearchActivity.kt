package com.example.addresssearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.addresssearch.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var mBinding : ActivitySearchBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
    }

    private fun viewBinding(){
        mBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}