package com.example.customlistviewdetail.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.customlistviewdetail.R
import com.example.customlistviewdetail.databinding.ActivityListItemDetailBinding

class ListItemDetail : AppCompatActivity() {
    private var mBinding : ActivityListItemDetailBinding? = null
    private val binding get() = mBinding!!
    private lateinit var imagePath : String
    private lateinit var title : String
    private lateinit var describe : String
    private lateinit var youtubeLink : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item_detail)
        
        initBinding()

        dataLoad()

        uiSetting()
    }

    /**
     * @DESC: 뷰바인딩
     */
    private fun initBinding(){
        mBinding = ActivityListItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 데이터 로드
     */
    private fun dataLoad(){
        val intent = intent
        imagePath   = intent.getStringExtra("imagePath").toString()
        title       = intent.getStringExtra("title").toString()
        describe    = intent.getStringExtra("describe").toString()
        youtubeLink = intent.getStringExtra("youtubeLink").toString()

    }

    /**
     * @DESC: UI 세팅
     */
    private fun uiSetting(){
        val image = Uri.parse("android.resource://" + this.packageName + "/" + imagePath)
        Glide.with(this).load(image).into(binding.imgCover)
        binding.tvTitle.text = title;
        binding.tvDescribe.text = describe
        binding.tvYoutube.text = youtubeLink
    }
}