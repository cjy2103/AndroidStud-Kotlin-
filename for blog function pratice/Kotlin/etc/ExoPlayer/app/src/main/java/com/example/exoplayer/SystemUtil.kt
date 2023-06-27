package com.example.exoplayer

import android.os.Build
import android.view.*

import androidx.constraintlayout.widget.ConstraintLayout

class SystemUtil {

    companion object{

        fun statusbarSetting(window: Window) {
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

            // 아래 세줄은 아이콘 색 검정색으로 변경하는 부분 , 주석하면 흰색
//            val decorView = window.decorView
//            val wic = WindowInsetsControllerCompat(window, decorView)
//            wic.isAppearanceLightStatusBars = true  // true or false as desired.

            // And then you can set any background color to the status bar.
//            window.statusBarColor = Color.BLACK // window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) 으로 주석 친 부분 해제 해서 상태바 투명하게 안할거면 사용

        }

        /**
         * @DESC: SoftBottomBar 숨기기
         * @param window
         */
        fun sofNavigationBarHide(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false)
                if (window.insetsController != null) {
                    window.insetsController!!.hide(WindowInsets.Type.navigationBars())
                    window.insetsController!!.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }


        }
    }

}