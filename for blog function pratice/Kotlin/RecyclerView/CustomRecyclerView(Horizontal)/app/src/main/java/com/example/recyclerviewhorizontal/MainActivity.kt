package com.example.recyclerviewhorizontal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewhorizontal.adapter.CustomRecyclerAdapter
import com.example.recyclerviewhorizontal.databinding.ActivityMainBinding
import com.example.recyclerviewhorizontal.model.ListItemModel
import com.example.recyclerviewhorizontal.model.MyListItem

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var listItem : ArrayList<MyListItem>
    private lateinit var adapter : CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding()

        initialize()
        listAdd()
        recyclerViewConnection()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initialize(){
        listItem = ArrayList()
        binding.recyclerList.layoutManager = LinearLayoutManager(this
            , LinearLayoutManager.HORIZONTAL, false)
    }

    private fun listAdd(){
        val imageUri = "drawable://"
        addItem(resources.getString(R.string.baknana), resources.getString(R.string.bak_describe)
            ,imageUri + R.drawable.baknana)
        addItem(resources.getString(R.string.djmax), resources.getString(R.string.djmax_describe)
            ,imageUri + R.drawable.djmax_clear_fail)
        addItem(resources.getString(R.string.djmax_falling_love), resources.getString(R.string.djmax_falling_love_describe)
            ,imageUri + R.drawable.djmax_falling_in_love)
        addItem(resources.getString(R.string.mwamwa), resources.getString(R.string.mwamwa_describe)
            , imageUri + R.drawable.mwama)
        addItem(resources.getString(R.string.tamtam), resources.getString(R.string.tamtam_describe)
            , imageUri + R.drawable.tamtam)
    }

    private fun addItem(title : String, describe : String, imagePath : String) {
        val listItemModel = ListItemModel()
        listItemModel.title = title
        listItemModel.describe = describe
        listItemModel.imagePath = imagePath

        val myListItem = MyListItem()

        val items = ArrayList<ListItemModel>()

        items.add(listItemModel)

        myListItem.list = items

        listItem.add(myListItem)
    }

    private fun recyclerViewConnection(){
        adapter = CustomRecyclerAdapter(this, this, listItem)
        binding.recyclerList.adapter = adapter

    }

}