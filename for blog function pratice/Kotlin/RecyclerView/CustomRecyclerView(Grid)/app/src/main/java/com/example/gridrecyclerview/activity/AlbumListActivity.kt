package com.example.gridrecyclerview.activity

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gridrecyclerview.R
import com.example.gridrecyclerview.adapter.AlbumListAdapter
import com.example.gridrecyclerview.adapter.OnItemClickListener
import com.example.gridrecyclerview.databinding.ActivityAlbumListBinding

class AlbumListActivity : AppCompatActivity() {

    private var mBinding : ActivityAlbumListBinding? = null
    private val binding get() = mBinding!!

    private lateinit var imageList : ArrayList<String>
    private lateinit var adapter : AlbumListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBidning()

        init()

        listAdd()

        adapterConnection()

        gridItemClick()
    }

    private fun viewBidning(){
        mBinding = ActivityAlbumListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        imageList = ArrayList()
        binding.recyclerGird.layoutManager = GridLayoutManager(this, 2)
    }

    private fun listAdd(){
        val imageUri = "drawable://"
        imageList.add(imageUri + R.drawable.baknana)
        imageList.add(imageUri + R.drawable.djmax_clear_fail)
        imageList.add(imageUri + R.drawable.djmax_falling_in_love)
        imageList.add(imageUri + R.drawable.mwama)
        imageList.add(imageUri + R.drawable.tamtam)
    }

    private fun adapterConnection(){
        adapter = AlbumListAdapter(this, this, imageList)
        binding.recyclerGird.adapter = adapter
    }

    private fun gridItemClick(){
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                val sharedPreferences: SharedPreferences = getSharedPreferences("image", MODE_PRIVATE)
                @SuppressLint("CommitPrefEdits") val editor = sharedPreferences.edit()
                editor.putString("path", imageList[position])
                editor.apply()
                (MainActivity.context as MainActivity).albumSelectCallback()
                finish()
            }
        })
    }
}