package com.example.tablayout.util

import android.util.Log
import com.example.tablayout.BuildConfig


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

        fun longlog(s: String) {
            val MAX_LEN = 4000 // 2000 bytes 마다 끊어서 출력
            val len = s.length
            if (len > MAX_LEN) {
                var idx = 0
                var nextIdx = 0
                while (idx < len) {
                    nextIdx += MAX_LEN
                    LogUtils.log(s.substring(idx, Math.min(nextIdx, len)))
                    //Log.e(TAG, s.substring(idx, nextIdx > len ? len : nextIdx));
                    idx = nextIdx
                }
            } else {
                LogUtils.log(s)
                //Log.e(TAG, mFuncTag, s);
            }
        }
    }

}