package com.example.aston_lesson_2.utils

import android.content.Context
import android.graphics.Canvas
import android.text.StaticLayout
import android.util.TypedValue
import androidx.core.graphics.withTranslation

fun Context.dpToPx(dp: Int) = dp.toFloat() * this.resources.displayMetrics.density

fun Context.spToPx(sp: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    sp.toFloat(),
    this.resources.displayMetrics
)

fun StaticLayout.draw(canvas: Canvas, x: Float, y: Float) =
    canvas.withTranslation(x, y) { draw(this) }