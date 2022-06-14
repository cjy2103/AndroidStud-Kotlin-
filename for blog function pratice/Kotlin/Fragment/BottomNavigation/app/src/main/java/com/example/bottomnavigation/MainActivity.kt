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
        menu.findItem(R.id.djmax_fragment).setIcon(R.drawable.iv_djmax_fail)

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
                screenChange(djMaxFragment, item)
            }

            R.id.momoi_fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, momoiFragment).commit()
                screenChange(momoiFragment, item)
            }

            R.id.midori_fragment -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame, midoriFragment).commit()
                screenChange(midoriFragment, item)
            }

        }
    }

    private fun screenChange(fragment : Fragment, item: MenuItem){
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
        menu.findItem(R.id.djmax_fragment).setIcon(R.drawable.iv_djmax)
        menu.findItem(R.id.momoi_fragment).setIcon(R.drawable.iv_momoi)
        menu.findItem(R.id.midori_fragment).setIcon(R.drawable.iv_midori)

        when(item.itemId){
            R.id.djmax_fragment -> {
                item.setIcon(R.drawable.iv_djmax_fail)
            }
            R.id.momoi_fragment -> {
                item.setIcon(R.drawable.iv_alice)
            }
            R.id.midori_fragment -> {
                item.setIcon(R.drawable.iv_yuse)
            }
        }
    }
}