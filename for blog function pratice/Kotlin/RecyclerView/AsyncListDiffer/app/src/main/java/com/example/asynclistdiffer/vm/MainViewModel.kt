package com.example.asynclistdiffer.vm

import androidx.lifecycle.MutableLiveData
import com.example.asynclistdiffer.data.CharacterProvider
import com.example.asynclistdiffer.data.Character

class MainViewModel {

    private val provider: CharacterProvider = CharacterProvider()
    private val characterList: MutableLiveData<ArrayList<Character>> = provider.getCharacterList()!!

    fun listAdd(){
        provider.clickBtnAdd()
        characterList.value = provider.getCharacterList()!!.value
    }

    fun getCharacterList(): MutableLiveData<ArrayList<Character>> {
        return characterList
    }

}