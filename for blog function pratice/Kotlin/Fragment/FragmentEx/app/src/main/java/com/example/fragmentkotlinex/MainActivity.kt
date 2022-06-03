package com.example.fragmentkotlinex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragmentkotlinex.databinding.ActivityMainBinding
import com.example.fragmentkotlinex.fragment.BlueArchiveFragment
import com.example.fragmentkotlinex.fragment.SpyFamilyFragment

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var blueArchiveFragment: BlueArchiveFragment
    private lateinit var spyFamilyFragment: SpyFamilyFragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding()

        initialize()

        clickBlueArchive()

        clickSpyFamily()
    }

    private fun viewbinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize(){
        blueArchiveFragment = BlueArchiveFragment()
        spyFamilyFragment = SpyFamilyFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, blueArchiveFragment).commit()
    }

    private fun clickBlueArchive(){
        binding.btnFragmentBlueArchavie.setOnClickListener {
            fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_frame, blueArchiveFragment).commit()
        }
    }

    private fun clickSpyFamily(){
        binding.btnFragmentSpyFamiliy.setOnClickListener {
            fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_frame, spyFamilyFragment).commit()
        }
    }
}