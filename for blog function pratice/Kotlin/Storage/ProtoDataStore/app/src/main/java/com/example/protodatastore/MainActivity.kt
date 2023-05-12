package com.example.protodatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.protodatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , CoroutineScope by MainScope() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private val dataStore: DataStore<Data.User> by dataStore(
        fileName = "user_prefs.pb",
        serializer = UserProtoSerializer
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        dataSave()

        dataLoad()
    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun dataSave(){
        binding.btnSave.setOnClickListener {
            val name = "Android"
            val age = 20
            launch {
                saveUserData(name,age)
            }
        }
    }

    private suspend fun saveUserData(name: String, age: Int){
        dataStore.updateData { currentUser ->
            currentUser.toBuilder()
                .setName(name)
                .setAge(age)
                .build()
        }
    }

    private fun dataLoad(){
        binding.btnLoad.setOnClickListener {
            launch {
                loadUserData{ user ->
                    val text = "Name: ${user.name}, Age: ${user.age}"
                    binding.tvData.text = text
                }
            }
        }
    }

    private suspend fun loadUserData(callback: (Data.User) -> Unit) {
        val user = dataStore.data.first()
        callback(user)
    }

}