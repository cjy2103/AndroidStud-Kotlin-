package com.example.bottomnavigation.util

import android.os.Build
import android.view.*

import androidx.constraintlayout.widget.ConstraintLayout
class SystemUtil {

    /**
     * @DESC: 상단바 투명
     * @param window
     * @param constraintLayout
     */
    companion object{
        fun statusbarSetting(window: Window, constraintLayout: ConstraintLayout) {
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS) // 주석치면 아이콘색 흰색
            }
            else {
                @Suppress("DEPRECATION")
                constraintLayout.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // 아이콘색 검정으로 -> 주석하면 흰색
            }

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