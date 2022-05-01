package com.example.customrecyclerviewdetail.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.customrecyclerview.model.MyListItem
import com.example.customrecyclerviewdetail.R
import com.example.customrecyclerviewdetail.databinding.ActivityRecyclerItemDetailBinding

class RecyclerItemDetailActivity : AppCompatActivity() {

    private var mBinding : ActivityRecyclerItemDetailBinding? = null
    private val binding get() = mBinding!!
    private lateinit var myListItem : MyListItem
    private lateinit var imagePath : String
    private lateinit var title : String
    private lateinit var describe : String
    private lateinit var youtubeLink : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_item_detail)

        viewBinding()

        dataLoad()

        uiSetting()
    }

    private fun viewBinding(){
        mBinding = ActivityRecyclerItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun dataLoad(){
        val bundle = intent.extras
        myListItem = bundle!!.getSerializable("itemObject") as MyListItem
        imagePath = myListItem.list[0].uri
        title = myListItem.list[0].title
        describe = myListItem.list[0].describe
        youtubeLink = myListItem.list[0].youtubeLink
    }

    private fun uiSetting(){
        val image = Uri.parse("android.resource://" + this.packageName + "/" + imagePath)
        Glide.with(this).load(image).into(binding.ivImg)
        binding.tvTitle.text = title
        binding.tvDescribe.text = describe
        binding.tvYoutubeLink.text = youtubeLink
    }
}