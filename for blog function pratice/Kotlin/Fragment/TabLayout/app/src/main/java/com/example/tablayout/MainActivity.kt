package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bottomnavigation.fragment.DjmaxFragment
import com.example.bottomnavigation.util.SystemUtil
import com.example.tablayout.databinding.ActivityMainBinding
import com.example.tablayout.databinding.FragmentDjmaxBinding
import com.example.tablayout.fragment.MidoriFragment
import com.example.tablayout.fragment.MomoiFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var djmaxFragment: DjmaxFragment
    private lateinit var momoiFragment: MomoiFragment
    private lateinit var midoriFragment: MidoriFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        tabLayoutSelect()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        djmaxFragment = DjmaxFragment()
        momoiFragment = MomoiFragment()
        midoriFragment = MidoriFragment()

        supportFragmentManager.beginTransaction().replace(R.id.main_frame, djmaxFragment).commit()

        SystemUtil.statusbarSetting(window, binding.consMain)
        SystemUtil.sofNavigationBarHide(window)
    }

    private fun tabLayoutSelect(){
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab!!.position
                changeView(pos)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun changeView(pos : Int){
        when(pos){
            0 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, djmaxFragment).commit()
            1 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, momoiFragment).commit()
            2 -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, midoriFragment).commit()
        }
    }
}