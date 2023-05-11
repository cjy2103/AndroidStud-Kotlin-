package com.example.preferencesdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.preferencesdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

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
            launch {
                dataStore.edit { preferences ->
                    preferences[PreferencesKeys.MY_TEXT] = "데이터 값 저장"
                }
            }
        }
    }

    private object PreferencesKeys {
        val MY_TEXT = stringPreferencesKey("my_text")
    }

    private fun dataLoad(){
        binding.btnLoad.setOnClickListener {
            launch {
                dataStore.data.collect { preferences ->
                    val text = preferences[PreferencesKeys.MY_TEXT] ?: "저장된 데이터 없음"
                    binding.tvData.text = text
                }
            }
        }
    }
}