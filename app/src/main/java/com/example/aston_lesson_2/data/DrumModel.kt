package com.example.aston_lesson_2.data

import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint

data class DrumModel(
    val nameOfSlice: String,
    val colorOfSlice: Int,
    val paint: Paint = Paint()
) {
    init {
        paint.apply {
            style = Paint.Style.FILL_AND_STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            pathEffect = CornerPathEffect(8F)
            color = colorOfSlice
            strokeWidth = 5f
        }
    }
}