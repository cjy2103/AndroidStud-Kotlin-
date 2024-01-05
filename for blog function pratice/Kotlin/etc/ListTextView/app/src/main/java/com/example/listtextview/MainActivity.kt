package com.example.listtextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listtextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnInfo.setOnClickListener {

            val list = ArrayList<Person>()

            list.add(Person("철수",21))
            list.add(Person("영희",15))
            list.add(Person("다비",26))

            Log.v("뭔데?", list[0].name)

            val persons = list.map {
                val name = it.name
                val age = it.age
                "(Name : $name, age : $age)"
            }
            Log.v("뭔데", persons.toString())

            binding.tvPerson.text = persons.joinToString ("\n")
        }

    }
}