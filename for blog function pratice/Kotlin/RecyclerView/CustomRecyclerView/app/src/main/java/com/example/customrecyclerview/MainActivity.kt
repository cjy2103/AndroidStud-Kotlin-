package com.example.customrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customrecyclerview.adapter.CustomRecyclerAdapter
import com.example.customrecyclerview.databinding.ActivityMainBinding
import com.example.customrecyclerview.model.ListItemModel
import com.example.customrecyclerview.model.MyListItem

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var myListItems: ArrayList<MyListItem>
    private lateinit var adapter : CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBinding()
        initialize()
        listAdd()
        recyclerViewConnection()
    }

    /**
     * @DESC: 뷰바인딩
     */
    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 초기화
     */
    private fun initialize(){
        myListItems = ArrayList()
        binding.recyclerList.layoutManager = LinearLayoutManager(this)
    }

    /**
     * @DESC: 리스트 추가
     */
    private fun listAdd(){
        val imageUri = "drawable://"
        addItem(resources.getString(R.string.baknana),resources.getString(R.string.bak_describe)
            , imageUri + R.drawable.baknana)
        addItem(resources.getString(R.string.djmax),resources.getString(R.string.djmax_describe)
            , imageUri + R.drawable.djmax_clear_fail)
        addItem(resources.getString(R.string.djmax_falling_love),resources.getString(R.string.djmax_falling_love_describe)
            , imageUri + R.drawable.djmax_falling_in_love)
        addItem(resources.getString(R.string.mwamwa),resources.getString(R.string.mwamwa_describe)
            , imageUri + R.drawable.mwama)
        addItem(resources.getString(R.string.tamtam),resources.getString(R.string.tamtam_describe)
            , imageUri + R.drawable.tamtam)
    }

    /**
     * @DESC: 아이템 추가
     */
    private fun addItem(title: String, describe: String, path: String) {
        val listItemModel = ListItemModel()
        listItemModel.title =  title
        listItemModel.describe = describe
        listItemModel.uri = path

        val myListItem = MyListItem()
        val items = ArrayList<ListItemModel>()

        items.add(listItemModel)

        myListItem.list = items

        myListItems.add(myListItem)
    }

    /**
     * @DESC: 리사이클러뷰 어댑터 연결
     */
    private fun recyclerViewConnection(){
        adapter = CustomRecyclerAdapter(this, this, myListItems)
        binding.recyclerList.adapter = adapter
    }
}