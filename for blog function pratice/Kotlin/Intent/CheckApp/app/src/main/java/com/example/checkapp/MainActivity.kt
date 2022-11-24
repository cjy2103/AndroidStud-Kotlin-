package com.example.checkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checkapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        btnUmaCheck()

        btnPokemonCheck()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun btnUmaCheck(){
        
    }

    private fun btnPokemonCheck(){

    }
}