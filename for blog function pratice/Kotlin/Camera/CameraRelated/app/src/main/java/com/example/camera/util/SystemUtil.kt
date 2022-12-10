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
    fun statusbarSetting(window: Window, constraintLayout: ConstraintLayout) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.insetsController?.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS) // 주석치면 아이콘색 흰색

    }

    /**
     * @DESC: SoftBottomBar 숨기기
     * @param window
     */
    fun sofNavigationBarHide(window: Window) {
        window.setDecorFitsSystemWindows(false)
        if (window.insetsController != null) {
            window.insetsController!!.hide(WindowInsets.Type.navigationBars())
            window.insetsController!!.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}