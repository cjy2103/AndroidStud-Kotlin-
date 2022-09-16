package com.example.roomdb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb.activity.DAO.DataDAO
import com.example.roomdb.databinding.ActivitySelectAllBinding
import com.example.roomdb.room.RoomDB
import kotlinx.coroutines.runBlocking

class SelectAllActivity : AppCompatActivity() {

    private var mBinding : ActivitySelectAllBinding? = null
    private val binding get() = mBinding!!

    private lateinit var roomDB: RoomDB
    private lateinit var dataList : ArrayList<DataDAO>


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
            roomDB.dataDao().getAll()
        }
    }
}