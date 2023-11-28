package com.example.aston_lesson_2.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()
    private val colorList =
        listOf(
            Color.RED,
            Color.YELLOW,
            Color.parseColor("#FFA500"),
            Color.GREEN,
            Color.BLUE,
            Color.parseColor("#800080"),
            Color.parseColor("#ADD8E6")
        )
    private val startAngle = 90f
    private val sweepAngle = 360f / colorList.size

    init {
        paint.apply {
            style = Paint.Style.FILL_AND_STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            pathEffect = CornerPathEffect(8F)
            color = Color.BLUE
            strokeWidth = 5f
        }
    }

    private fun drawCircle(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = width / 2f
        val radius = width.coerceAtMost(height) / 2f
        for (item in colorList.indices) {
            paint.color = colorList[item]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + item * sweepAngle,
                sweepAngle,
                true,
                paint
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }
}