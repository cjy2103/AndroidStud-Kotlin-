package com.example.roomdb.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityMainBinding
import com.example.roomdb.room.RoomDB
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var roomDB : RoomDB
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        btnDataInsert()

        btnSelectAll()

        btnSelectById()

        btnDeleteAll()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        roomDB = RoomDB.getInstance(this)!!
        context = this
    }

    private fun btnDataInsert(){
        binding.btnInsert.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnSelectAll(){
        binding.btnAllSearch.setOnClickListener{
            val intent = Intent(this, SelectAllActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnSelectById() {
        binding.btnSelectById.setOnClickListener {
            val intent = Intent(this, SelectByIdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnDeleteAll(){
        binding.btnAllDelete.setOnClickListener {
            runBlocking {
                roomDB.dataDao().deleteAll()

                Toast.makeText(context, "데이터 전체 삭제 완료", Toast.LENGTH_SHORT).show()
            }
        }
    }

}