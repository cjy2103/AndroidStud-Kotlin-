package com.example.kakaomapkotlin.vm

import android.content.Context
import android.view.View
import android.view.ViewGroup
import net.daum.mf.map.api.MapView

class MapViewModel {
    lateinit var mapView : MapView
    lateinit var mapViewContainer : ViewGroup

    fun init(context: Context, view: View ){
        mapView = MapView(context)
        mapViewContainer = view as ViewGroup
        view.addView(mapView)
    }
}