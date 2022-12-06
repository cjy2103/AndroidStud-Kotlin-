package com.example.viewpager2.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.viewpager2.R
import com.example.viewpager2.adapter.ViewPager2Adapter
import com.example.viewpager2.dto.ListItem
import com.example.viewpager2.dto.ListItemDTO
import com.example.viewpager2.databinding.ActivityMainBinding
import com.example.viewpager2.util.SystemUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var list : ArrayList<ListItem>

    private lateinit var nameList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        listAdd()

        viewPagerConnection()

//        viewPagerSlideEvent()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        SystemUtil.statusbarSetting(window, this, binding.mainRootLayout)
        SystemUtil.sofNavigationBarHide(window)

        list = ArrayList()
        nameList = ArrayList(listOf("박나나", "클리어&페일", "Falling in love", "뫄뫄", "탬탬"))
    }

    private fun listAdd(){
        val imageUri = "drawable://"
        addItem(imageUri + R.drawable.baknana)
        addItem(imageUri + R.drawable.djmax_clear_fail)
        addItem(imageUri + R.drawable.djmax_falling_in_love)
        addItem(imageUri + R.drawable.mwama)
        addItem(imageUri + R.drawable.tamtam)
    }

    private fun addItem(imagePath: String) {
        val listItemDTO = ListItemDTO()
        listItemDTO.imagePath = imagePath

        var listItem = ListItem()

        val items = ArrayList<ListItemDTO>()

        items.add(listItemDTO)

        listItem.list = items

        list.add(listItem)

    }

    private fun viewPagerConnection(){
        binding.viewPager2.adapter = ViewPager2Adapter(this,this,list)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) {
                tab: TabLayout.Tab?, position: Int ->
        }.attach()
    }

    /**
     * @DESC: 슬라이드 이벤트 발생
     */
    private fun viewPagerSlideEvent() {
        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                showToast(nameList[position])
            }
        })
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, "$msg 선택됨", Toast.LENGTH_SHORT).show()
    }
}