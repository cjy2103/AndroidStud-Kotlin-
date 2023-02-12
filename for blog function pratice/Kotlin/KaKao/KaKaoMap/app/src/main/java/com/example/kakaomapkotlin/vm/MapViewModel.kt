package com.example.kakaomapkotlin.vm

import android.content.Context
import android.view.View
import android.view.ViewGroup
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapViewModel {
    lateinit var mapView : MapView
    lateinit var mapViewContainer : ViewGroup

    fun init(context: Context, view: View ){
        mapView = MapView(context)
        mapViewContainer = view as ViewGroup
        view.addView(mapView)

        markAdd()
    }

    fun markAdd(){
        // 시작위치 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.1399,129.0986), true)
        // 마커달기
        val markerPoint = MapPoint.mapPointWithGeoCoord(35.1399,129.0986)
        val marker = MapPOIItem()
        marker.itemName = "경성대학교"
        marker.tag = 0
        marker.mapPoint = markerPoint
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin

        mapView.addPOIItem(marker)
    }
}