package com.example.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.bottomnavigation.databinding.ActivityMainBinding
import com.example.bottomnavigation.fragment.DjmaxFragment
import com.example.bottomnavigation.fragment.MidoriFragment
import com.example.bottomnavigation.fragment.MomoiFragment
import com.example.bottomnavigation.util.SystemUtil

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var menu: Menu

    private val djMaxFragment : DjmaxFragment = DjmaxFragment()
    private val momoiFragment : MomoiFragment = MomoiFragment()
    private val midoriFragment : MidoriFragment = MidoriFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        initialize()

        bottomNavigationSelect();
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize(){
        binding.bottomNav.itemIconTintList = null
        menu = binding.bottomNav.menu

        val systemUtil = SystemUtil()
        systemUtil.statusbarSetting(window,binding.mainConstraint)
        systemUtil.sofNavigationBarHide(window)

        supportFragmentManager.beginTransaction().replace(R.id.main_frame, djMaxFragment).commit()

    }

    private fun bottomNavigationSelect(){
        binding.bottomNav.setOnItemSelectedListener { item ->
            changeFragment(item)
            return@setOnItemSelectedListener true
        }
    }

    private fun changeFragment(item: MenuItem){
        when (item.itemId){
            R.id.djmax_fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, djMaxFragment).commit()
            }

            R.id.momoi_fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, momoiFragment).commit()
            }

            R.id.midori_fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, midoriFragment).commit()
            }

        }
    }
}