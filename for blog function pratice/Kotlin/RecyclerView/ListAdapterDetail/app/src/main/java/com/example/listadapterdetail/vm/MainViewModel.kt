package com.example.listadapterdetail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listadapterdetail.data.CharacterProvider
import com.example.listadapterdetail.data.Character

class MainViewModel {

    private val provider: CharacterProvider = CharacterProvider()
    private val characterList: MutableLiveData<ArrayList<Character>> = provider.getCharacterList()!!

    private val _characterClickListener = MutableLiveData<Character>()
    val characterClickListener : LiveData<Character> = _characterClickListener

    fun listAdd(){
        provider.clickBtnAdd()
        characterList.value = provider.getCharacterList()!!.value
    }

    fun deleteItem(){
        provider.deleteItem()
        characterList.value = provider.getCharacterList()!!.value
    }

    fun getCharacterList(): MutableLiveData<ArrayList<Character>> {
        return characterList
    }

    fun onCharacterClicked(character: Character){
        _characterClickListener.value = character
    }

}