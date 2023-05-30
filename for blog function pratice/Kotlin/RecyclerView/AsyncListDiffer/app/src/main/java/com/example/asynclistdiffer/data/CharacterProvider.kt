package com.example.asynclistdiffer.data

import androidx.lifecycle.MutableLiveData
import com.example.asynclistdiffer.R

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

    fun getCharacterList(): MutableLiveData<ArrayList<Character>>? {
        return characterList
    }

}