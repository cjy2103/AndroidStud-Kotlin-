package com.example.roomdb.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb.activity.DAO.DataDAO
import com.example.roomdb.activity.adapter.SelectAllAdapter
import com.example.roomdb.databinding.ActivitySelectAllBinding
import com.example.roomdb.room.Data
import com.example.roomdb.room.RoomDB
import kotlinx.coroutines.runBlocking

class SelectAllActivity : AppCompatActivity() {

    private var mBinding : ActivitySelectAllBinding? = null
    private val binding get() = mBinding!!

    private lateinit var roomDB: RoomDB
    private lateinit var dataList : ArrayList<DataDAO>

    private lateinit var adapter: SelectAllAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        dataLoad()
    }

    private fun viewBinding(){
        mBinding = ActivitySelectAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        roomDB = RoomDB.getInstance(this)!!
        dataList = ArrayList()
        binding.recyclerList.layoutManager = LinearLayoutManager(this)
    }

    private fun dataLoad(){
        runBlocking {
            var result = roomDB.dataDao().getAll()

            for (data in result) {
                dataListAdd(data)
            }

            recyclerConnection()
        }
    }

    private fun dataListAdd(data: Data){
        var dataDao = DataDAO()
        dataDao.title = data.title
        dataDao.msg = data.msg

        dataList.add(dataDao)

    }

    private fun recyclerConnection(){
        adapter = SelectAllAdapter(this,dataList)
        binding.recyclerList.adapter = adapter
    }
}