package com.example.listadapter.data

import androidx.lifecycle.MutableLiveData
import com.example.listadapter.R

class CharacterProvider {
    private val list = ArrayList<Character>()

    private val characterList = MutableLiveData<ArrayList<Character>>()

    init{
        addItem(R.string.baknana, R.string.bak_describe, R.drawable.baknana)
        addItem(R.string.djmax, R.string.djmax_describe, R.drawable.djmax_clear_fail)
        addItem(
            R.string.djmax_falling_love,
            R.string.djmax_falling_love_describe,
            R.drawable.djmax_falling_in_love
        )
        characterList.value = list
    }

    private fun addItem(title: Int, describe: Int, image: Int) {
        val character = Character(title,describe,image)
        list.add(character)
    }

    fun clickBtnAdd() {
        addItem(R.string.mwamwa, R.string.mwamwa_describe, R.drawable.mwama)
        addItem(R.string.tamtam, R.string.tamtam_describe, R.drawable.tamtam)
    }

    fun deleteItem(){
        if (list.size > 1) {
            for (i in 0..1) {
                list.removeAt(list.size - 1)
            }
        }
    }

    fun getCharacterList(): MutableLiveData<ArrayList<Character>>? {
        return characterList
    }

}