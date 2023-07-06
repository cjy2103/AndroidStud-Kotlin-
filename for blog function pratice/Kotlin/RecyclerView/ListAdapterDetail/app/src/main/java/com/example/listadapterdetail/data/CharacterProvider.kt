package com.example.listadapterdetail.data

import androidx.lifecycle.MutableLiveData
import com.example.listadapterdetail.R


class CharacterProvider {
    private val list = ArrayList<Character>()

    private val characterList = MutableLiveData<ArrayList<Character>>()

    init{
        addItem(R.string.baknana, R.string.bak_describe, R.drawable.baknana, R.string.baknana_link)
        addItem(R.string.djmax, R.string.djmax_describe, R.drawable.djmax_clear_fail, R.string.djmax_archive)
        addItem(
            R.string.djmax_falling_love,
            R.string.djmax_falling_love_describe,
            R.drawable.djmax_falling_in_love,
            R.string.djmax_falling_love_link
        )
        characterList.value = list
    }

    private fun addItem(title: Int, describe: Int, image: Int, link: Int) {
        val character = Character(title,describe,image,link)
        list.add(character)
    }

    fun clickBtnAdd() {
        addItem(R.string.mwamwa, R.string.mwamwa_describe, R.drawable.mwama,R.string.mwamwa_link)
        addItem(R.string.tamtam, R.string.tamtam_describe, R.drawable.tamtam,R.string.tamtam_link)
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