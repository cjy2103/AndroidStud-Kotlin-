package com.example.navigationgraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bottomnavigation.util.SystemUtil
import com.example.navigationgraph.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding()

        init()
    }

    private fun dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun init(){
        binding.bottomNav.itemIconTintList = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frame)
        val navController: NavController = navHostFragment!!.findNavController()

        NavigationUI.setupWithNavController(binding.bottomNav, navController)

        SystemUtil.sofNavigationBarHide(window)
        SystemUtil.statusbarSetting(window,binding.mainConstraint)
    }
}