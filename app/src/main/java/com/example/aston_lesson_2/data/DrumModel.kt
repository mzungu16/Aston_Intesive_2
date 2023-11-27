package com.example.aston_lesson_2.data

import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint

data class DrumModel(
    var percentOfCircle: Float = 0F,
    var percentToStartAt: Float = 0F,
    var colorOfLine: Int = 0,
    var stroke: Float = 0F,
    var paint: Paint = Paint(),
    var paintRound: Boolean = true
) {
    init {
        if (percentOfCircle < 0 || percentOfCircle > 100) {
            percentOfCircle = 100F
        }

        percentOfCircle = 360 * percentOfCircle / 100

        if (percentToStartAt < 0 || percentToStartAt > 100) {
            percentToStartAt = 0F
        }

        percentToStartAt = 360 * percentToStartAt / 100

        if (colorOfLine == 0) {
            colorOfLine = Color.parseColor("#000000")
        }

        paint = Paint().apply {
            color = colorOfLine
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = stroke
            isDither = true
        }

        if (paintRound) {
            paint.apply {
                strokeJoin = Paint.Join.ROUND
                strokeCap = Paint.Cap.ROUND
                pathEffect = CornerPathEffect(8F)
            }
        }
    }
}