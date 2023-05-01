package com.example.navigationgraph.util


import android.util.Log
import com.example.navigationgraph.BuildConfig


class LogUtils {

    companion object{
        fun log(x: String?) {
            if (BuildConfig.DEBUG) Log.i("RoomDB", x!!)
        }

        fun log(x: Int) {
            if (BuildConfig.DEBUG) Log.i("RoomDB", x.toString())
        }

        fun log(tag: String?, x: String?) {
            if (BuildConfig.DEBUG) Log.i(tag, x!!)
        }

        fun log(tag: String?, x: Int) {
            if (BuildConfig.DEBUG) Log.i(tag, x.toString())
        }

    }

}