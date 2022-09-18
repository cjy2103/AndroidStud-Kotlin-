package com.example.roomdb.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivitySelectByIdBinding
import com.example.roomdb.room.RoomDB
import kotlinx.coroutines.runBlocking

class SelectByIdActivity : AppCompatActivity() {
    private var mbinding : ActivitySelectByIdBinding? = null
    private val binding get() = mbinding!!

    private lateinit var roomDB: RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        btnSearch()
    }

    private fun viewBinding(){
        mbinding = ActivitySelectByIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        roomDB = RoomDB.getInstance(this)!!
    }

    @SuppressLint("SetTextI18n")
    private fun btnSearch(){
        binding.btnSearch.setOnClickListener{
            runBlocking {
                var result = roomDB.dataDao().loadById(binding.edtTitle.text.toString())

                if(result.isNotEmpty()){
                    val title = result[0].title
                    val msg = result[0].msg
                    binding.tvResult.text = "제목 : $title\n메시지 : $msg"
                } else {
                    binding.tvResult.text = "데이터 없음"
                }
            }
        }
    }
}