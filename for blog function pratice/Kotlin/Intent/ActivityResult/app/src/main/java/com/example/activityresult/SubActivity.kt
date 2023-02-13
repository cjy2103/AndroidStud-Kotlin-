package com.example.activityresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.activityresult.databinding.ActivitySubBinding
import com.example.activityresult.vm.SubViewModel

class SubActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySubBinding
    private val subViewModel : SubViewModel = SubViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub)
        binding.viewModel = subViewModel
        binding.lifecycleOwner = this

    }
}