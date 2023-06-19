package com.example.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hilt.databinding.ActivityMainBinding
import com.example.hilt.repository.UserRepositoryImpl
import com.example.hilt.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val userRepositoryImpl = UserRepositoryImpl("철수")
        viewModel = MainViewModel(userRepositoryImpl)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}