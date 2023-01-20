package com.example.kakaomap.util

import android.util.Log
import com.example.kakaomap.BuildConfig

class LogUtil {
    companion object{
        fun log(x: String?) {
            if (BuildConfig.DEBUG) Log.i("LogUtil", x!!)
        }

        fun log(x: Int) {
            if (BuildConfig.DEBUG) Log.i("LogUtil", x.toString())
        }

        fun log(tag: String?, x: String?) {
            if (BuildConfig.DEBUG) Log.i(tag, x!!)
        }

        fun log(tag: String?, x: Int) {
            if (BuildConfig.DEBUG) Log.i(tag, x.toString())
        }
    }
}