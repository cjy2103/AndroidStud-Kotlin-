package com.example.listadapterdetail.vm

import android.content.Intent
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadapterdetail.data.Character
import com.example.listadapterdetail.data.ParcelCharacter
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
//        _title = intent.getIntExtra("title",0)
//        _describe = intent.getIntExtra("describe",0)
//        _image = intent.getIntExtra("image",0)
//        _youtubeLink = intent.getIntExtra("youtube",0)
//
//        title.value = _title
//        describe.value = _describe
//        image.value = _image
//        youtubeLink.value = _youtubeLink


        val character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("character", ParcelCharacter::class.java)!!
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