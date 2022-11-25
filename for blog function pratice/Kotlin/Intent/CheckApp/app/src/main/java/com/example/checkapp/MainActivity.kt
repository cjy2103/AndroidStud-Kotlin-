package com.example.checkapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.checkapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        btnUmaCheck()

        btnPokemonCheck()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun btnUmaCheck(){
        binding.btnUmaCheck.setOnClickListener {
            val packageName = "com.kakaogames.umamusume"
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if(intent != null){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                } catch (e : android.content.ActivityNotFoundException){
                    Toast.makeText(this,"플레이스토어 앱 존재하지않음",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun btnPokemonCheck(){
        binding.btnPokemonCheck.setOnClickListener {
            val packageName = "com.nianticlabs.pokemongo"
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if(intent != null){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                } catch (e : android.content.ActivityNotFoundException){
                    Toast.makeText(this,"플레이스토어 앱 존재하지않음",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}