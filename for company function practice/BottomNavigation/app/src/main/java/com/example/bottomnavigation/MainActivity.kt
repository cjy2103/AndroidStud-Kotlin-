package com.example.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.bottomnavigation.Fragment.*
import com.example.bottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val homeFragment    = HomeFragment()
    private val imageFragment   = ImageFragment()
    private val infoFragment    = InfoFragment()
    private val libraryFragment = LibraryFragment()


    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(homeFragment)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_fragment -> replaceFragment(homeFragment)
                R.id.image_fragment -> replaceFragment(imageFragment)
                R.id.info_fragment -> replaceFragment(infoFragment)
                R.id.library_fragment -> replaceFragment(libraryFragment)
            }
            true
        }

    }

    fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frame,fragment)
            transaction.commit()

        }
    }
}