package com.example.figure.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class FigureView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint : Paint = Paint()
    private val path: Path = Path()


    private var centerX by Delegates.notNull<Float>()
    private var centerY by Delegates.notNull<Float>()
    private var case = -1

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 캔버스 초기화
        canvas.drawColor(Color.TRANSPARENT)

        // Paint 속성 초기화
        paint.reset()
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL

        when(case){
            1 -> {
                centerX = width / 2f
                centerY = height / 2f
                val radius = centerX.coerceAtMost(centerY)
                canvas.drawCircle(centerX, centerY, radius, paint)
            }
            2-> {
                val rectLeft = 200f
                val rectTop = 400f
                val rectRight = width.toFloat() - rectLeft
                val rectBottom = height.toFloat() - rectTop
                canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, paint)
            }

            3-> {
                path.moveTo(width / 2f, 500f) // 맨 위 꼭지점
                path.lineTo(width - 200f, height.toFloat() - 400f) // 오른쪽 아래 꼭지점
                path.lineTo(200f, height.toFloat() - 400f) // 왼쪽 아래 꼭지점
                path.close()

                canvas.drawPath(path, paint)
            }

        }
    }

    fun clickBtn(style : Int){
        case = style
    }
}