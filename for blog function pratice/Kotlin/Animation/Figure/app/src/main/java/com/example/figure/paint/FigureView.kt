package com.example.figure.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class FigureView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint : Paint = Paint()

    private var centerX by Delegates.notNull<Float>()
    private var centerY by Delegates.notNull<Float>()
    private var case = 1

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL

    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        when(case){

        }
    }

    fun clickBtn(style : Int){
        invalidate()
        when(style) {

            
        }
    }

    fun drawCircle(canvas : Canvas){
        val radius = centerX.coerceAtMost(centerY)

        canvas.drawCircle(centerX, centerY, radius, paint)
    }
}