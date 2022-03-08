package com.example.customlistviewdetail.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.example.customlistview.listView.ListViewAdapter
import com.example.customlistview.listView.ListViewItem
import com.example.customlistviewdetail.R
import com.example.customlistviewdetail.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var adapter: ListViewAdapter
    private lateinit var imageList : ArrayList<Int>
    private lateinit var titleList : ArrayList<String>
    private lateinit var describeList : ArrayList<String>
    private lateinit var youtubeLinkList : ArrayList<String>
    private val imageUri = "drawable://"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        initialize()

        addListItem()

        wordInput()

        clickItem()
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
        imageList = ArrayList(
            listOf(R.drawable.baknana
            , R.drawable.djmax_clear_fail
            , R.drawable.djmax_falling_in_love, R.drawable.mwama
            , R.drawable.tamtam)
        )
        titleList = ArrayList(
            listOf(resources.getString(R.string.baknana)
            , resources.getString(R.string.djmax)
            , resources.getString(R.string.djmax_song)
            , resources.getString(R.string.mwamwa)
            , resources.getString(R.string.tamtam))
        )
        describeList = ArrayList(
            listOf(resources.getString(R.string.bak_describe)
            , resources.getString(R.string.djmax_descibe)
            , resources.getString(R.string.djmax_song_describe)
            , resources.getString(R.string.mwamwa_descibe)
            , resources.getString(R.string.tamtam_describe))
        )
        youtubeLinkList = ArrayList(
            listOf(resources.getString(R.string.bak_link)
            , resources.getString(R.string.djmax_link)
            , resources.getString(R.string.djmax_song_link)
            , resources.getString(R.string.mwamwa_link)
            , resources.getString(R.string.tamtam_link)
            ))
    }

    /**
     * @DESC: 리스트에 아이템 추가
     */
    private fun addListItem(){
        binding.listItem.adapter = adapter
        for(i in 0 until imageList.count()){
            adapter.addItem(
                imageUri + imageList[i], titleList[i], describeList[i], youtubeLinkList[i]
            )
        }
    }

    /**
     * @DESC: 단어입력
     */
    private fun wordInput(){
        binding.edtInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val str = binding.edtInput.text.toString().lowercase()
                filterWord(str)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    /**
     * @DESC: 단어 필터
     */
    private fun filterWord(str : String){
        adapter.clearItem()
        val positionList : ArrayList<Int> = ArrayList()
        if(str.isEmpty()){
            for(i in 0 until titleList.count()){
                positionList.add(i)
            }
        } else {
            for(word in titleList){
                if(word.lowercase().contains(str)){
                    positionList.add(titleList.indexOf(word))
                }
            }
        }

        for(i in 0 until positionList.count()){
            adapter.addItem(imageUri + imageList[positionList[i]]
                , titleList[positionList[i]], describeList[positionList[i]]
                , youtubeLinkList[positionList[i]])
        }

        adapter.notifyDataSetChanged()
    }

    /**
     * @DESC: 아이템 클릭
     */
    private fun clickItem(){
        binding.listItem.setOnItemClickListener { adapterView, view, position, id ->
            val item : ListViewItem = adapterView.getItemAtPosition(position) as ListViewItem

            val intent = Intent(this, ListItemDetail::class.java)
            intent.putExtra("imagePath",item.image)
            intent.putExtra("title",item.title)
            intent.putExtra("describe",item.describe)
            intent.putExtra("youtubeLink",item.youtubeLink)
            startActivity(intent)
        }
    }

}