package com.example.roomdb.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityInsertBinding
import com.example.roomdb.room.Data
import com.example.roomdb.room.RoomDB
import kotlinx.coroutines.runBlocking

class InsertActivity : AppCompatActivity() {

    private var mBinding : ActivityInsertBinding? = null
    private val binding get() = mBinding!!

    private lateinit var roomDB: RoomDB
    private lateinit var data: Data
    private lateinit var mContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        init()

        btnOk()

        btnCancel()
    }

    private fun btnOk(){
        binding.btnOk.setOnClickListener {
            if(binding.edtTitle.text.toString().isEmpty()){
                Toast.makeText(this, "Title은 비어있으면 안됩니다.", Toast.LENGTH_SHORT).show()
            }else {
                dbInsert()
            }
        }
    }

    private fun btnCancel() {
        binding.btnCancel.setOnClickListener { finish() }
    }

    private fun viewBinding(){
        mBinding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init(){
        roomDB = RoomDB.getInstance(this)!!
        data = Data()
        mContext = this
    }

    private fun dbInsert(){
        data.title = binding.edtTitle.text.toString()
        data.msg = binding.edtMsg.text.toString()

        runBlocking {
            roomDB.dataDao().insert(data)
            Toast.makeText(mContext, "데이터 추가 완료", Toast.LENGTH_SHORT).show()
            finish()
        }

    }



}