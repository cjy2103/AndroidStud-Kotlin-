package com.example.listadapterdetail.vm

import android.content.Intent
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadapterdetail.data.Character
import kotlin.properties.Delegates

class DetailViewModel : ViewModel(){

    var title : MutableLiveData<Int> = MutableLiveData()
    var describe : MutableLiveData<Int> = MutableLiveData()
    var image : MutableLiveData<Int> = MutableLiveData()
    var youtubeLink : MutableLiveData<Int> = MutableLiveData();

    private var _title by Delegates.notNull<Int>()
    private var _describe by Delegates.notNull<Int>()
    private var _image by Delegates.notNull<Int>()
    private var _youtubeLink by Delegates.notNull<Int>()

    fun setData(intent: Intent) {

        val character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("character", Character::class.java)!!
        } else {
            intent.getParcelableExtra("character")!!
        }

        _title = character.title
        _describe = character.describe
        _image = character.image
        _youtubeLink = character.youtubeLink

        title.value = _title
        describe.value = _describe
        image.value = _image
        youtubeLink.value = _youtubeLink

    }
}