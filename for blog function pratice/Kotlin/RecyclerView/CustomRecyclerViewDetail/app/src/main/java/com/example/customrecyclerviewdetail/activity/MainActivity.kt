package com.example.customrecyclerviewdetail.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customrecyclerview.adapter.CustomRecyclerAdapter
import com.example.customrecyclerview.model.ListItemModel
import com.example.customrecyclerview.model.MyListItem
import com.example.customrecyclerviewdetail.R
import com.example.customrecyclerviewdetail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var myListItems: ArrayList<MyListItem>
    private lateinit var adapter : CustomRecyclerAdapter
    private lateinit var searchIndexList : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBinding()
        initialize()
        listAdd()
        recyclerViewConnection()
        wordIntput()
        itemClick()

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
        searchIndexList = ArrayList()
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
        adapter = CustomRecyclerAdapter(this, this, myListItems,myListItems.size,binding.edtInput.text.toString())
        binding.recyclerList.adapter = adapter
    }

    /**
     * @DESC: 단어 입력 감지
     */
    private fun wordIntput(){
        binding.edtInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = binding.edtInput.text.toString().lowercase()
                filterWord(str)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    /**
     * @DESC: 단어필터
     */
    private fun filterWord(word: String) {
        searchIndexList.clear()

        if(binding.edtInput.text.toString() == ""){
            adapter = CustomRecyclerAdapter(this, this, myListItems, myListItems.size, word)
            binding.recyclerList.adapter = adapter
        } else {
            for(i in 0 until myListItems.size){
                if(myListItems.get(i).list[0].title.lowercase().contains(word)){
                    searchIndexList.add(i)
                }
            }
            adapter = CustomRecyclerAdapter(this, this, myListItems, searchIndexList, searchIndexList.size, word)

            binding.recyclerList.adapter = adapter
        }
    }

    private fun itemClick(){
        val intent = Intent(this,RecyclerItemDetailActivity::class.java)
        adapter.setOnItemClickListener(object : CustomRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                val bundle = Bundle()
                bundle.putSerializable("itemObject", myListItems[position])
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

    }
}