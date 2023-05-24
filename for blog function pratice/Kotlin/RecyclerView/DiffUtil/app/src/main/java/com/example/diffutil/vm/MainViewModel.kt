package com.example.diffutil.vm

import androidx.lifecycle.MutableLiveData
import com.example.diffutil.data.Character
import com.example.diffutil.data.CharacterProvider

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