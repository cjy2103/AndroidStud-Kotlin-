package com.example.listviewcall

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewcall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var list : ArrayList<String>
    private lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initBinding()

        initialize()

        listAdd()

        listConnection()

        listItemClick()
    }

    /**
     * @DESC: 뷰바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 초기화
     */
    private fun initialize(){
        list = ArrayList()
    }

    /**
     * @DESC: 리스트 아이템 추가
     */
    private fun listAdd(){
        list.add("000-0000-0000")
        list.add("111-1111-1111")
        list.add("222-2222-2222")
        list.add("333-3333-3333")
        list.add("444-4444-4444")
        list.add("555-5555-5555")
        list.add("666-6666-6666")
        list.add("777-7777-7777")
        list.add("888-8888-8888")
        list.add("999-9999-9999")
    }

    /**
     * @DESC: 리스트 어댑터 연결
     */
    private fun listConnection(){
        adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list)
        binding.listItem.adapter = adapter
    }

    /**
     * @DESC: 리스트 아이템 클릭
     */
    private fun listItemClick(){
        binding.listItem.setOnItemClickListener { parent, view, position, id ->
            val item = parent.adapter.getItem(position)
            val number = "tel:$item"
            val uri = Uri.parse(number)
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }
    }
}