package com.example.camera.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.bottomnavigation.util.SystemUtil

abstract class BaseActivity<B : ViewBinding>(private val bindingFactory: (LayoutInflater) -> B) : AppCompatActivity() {

    private var _binding : B? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)

        setContentView(binding.root)
        hideStatusBar()
        hideBottomBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun hideStatusBar() {
        SystemUtil.statusbarSetting(window)
    }

    private fun hideBottomBar() {
        SystemUtil.sofNavigationBarHide(window)
    }


}