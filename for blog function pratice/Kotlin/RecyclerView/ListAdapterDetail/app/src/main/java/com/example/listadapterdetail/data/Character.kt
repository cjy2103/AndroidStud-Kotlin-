package com.example.listadapterdetail.data

import android.os.Parcel
import android.os.Parcelable

data class Character(val title : Int, val describe : Int,
                     val image : Int, val youtubeLink : Int)
    : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(title)
        parcel.writeInt(describe)
        parcel.writeInt(image)
        parcel.writeInt(youtubeLink)
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}


