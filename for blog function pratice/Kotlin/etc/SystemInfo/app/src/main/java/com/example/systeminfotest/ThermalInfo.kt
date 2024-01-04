package com.example.systeminfotest

import android.annotation.SuppressLint

class ThermalInfo(thermalName: String?, thermalValue: String?) {
    private var thermalName: String? = null
    private var thermalValue: String? = null

    fun getThermalName(): String? {
        return thermalName
    }

    fun getThermalValue(): String? {
        return thermalValue
    }
}