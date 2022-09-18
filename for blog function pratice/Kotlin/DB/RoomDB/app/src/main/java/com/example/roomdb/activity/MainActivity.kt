package com.example.roomdb.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        btnDataInsert()

        btnSelectAll()

        btnSelectById()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

}