package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.customlistview.databinding.ActivityMainBinding
import com.example.customlistview.listView.ListViewAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var adapter: ListViewAdapter
    private lateinit var imageList : ArrayList<Int>
    private lateinit var titleList : ArrayList<String>
    private lateinit var describeList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        initialize()

        addListItem()
    }

    /**
     * @DESC: 뷰 바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC : 초기화
     */
    private fun initialize(){
        adapter = ListViewAdapter()
        imageList = ArrayList(Arrays.asList(R.drawable.baknana
            , R.drawable.djmax_clear_fail
            , R.drawable.djmax_falling_in_love, R.drawable.mwama
            , R.drawable.tamtam))
        titleList = ArrayList(Arrays.asList(resources.getString(R.string.baknana)
            , resources.getString(R.string.djmax)
            , resources.getString(R.string.djmax_song)
            , resources.getString(R.string.mwamwa)
            , resources.getString(R.string.tamtam)))
        describeList = ArrayList(Arrays.asList(resources.getString(R.string.bak_describe)
            , resources.getString(R.string.djmax_descibe)
            , resources.getString(R.string.djmax_song_describe)
            , resources.getString(R.string.mwamwa_descibe)
            , resources.getString(R.string.tamtam_describe)))
    }

    /**
     * @DESC: 리스트에 아이템 추가
     */
    private fun addListItem(){
        binding.listItem.adapter = adapter
        for(i in 0 until imageList.count()){
            adapter.addItem(
                ContextCompat.getDrawable(this, imageList[i])!!
                    , titleList[i], describeList[i]
            )
        }
    }

}