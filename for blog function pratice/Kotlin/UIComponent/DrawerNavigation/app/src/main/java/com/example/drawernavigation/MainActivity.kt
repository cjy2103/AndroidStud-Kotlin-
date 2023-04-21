package com.example.drawernavigation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.drawernavigation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        openDrawer()

        itemClick()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



    private fun openDrawer() {
        binding.btnOpen.setOnClickListener { binding.drawerLayout.openDrawer(GravityCompat.START) }
    }

    private fun itemClick(){
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> {
                    Toast.makeText(this, "프로필 클릭", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.nav_naver -> {
                    Toast.makeText(this, "네이버 클릭", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.nav_github -> {
                    Toast.makeText(this, "GitHub 클릭", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            return@setNavigationItemSelectedListener false
        }
    }
}