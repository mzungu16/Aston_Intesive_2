package com.example.aston_lesson_2.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.aston_lesson_2.data.DrumModel
import com.example.aston_lesson_2.domain.DrumInteractionInt

class DrumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), DrumInteractionInt {
    private var drumModelList = listOf<DrumModel>()
    private val startAngle = -180f
    private var sweepAngle = 0f

    private fun drawCircle(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = width / 2f
        val radius = width.coerceAtMost(height) / 2f
        sweepAngle = 360f / drumModelList.size
        drumModelList.forEach { drumModel ->
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                startAngle + drumModelList.indexOf(drumModel) * sweepAngle,
                sweepAngle,
                true,
                drumModel.paint
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }

    override fun setData(listOfDrumModel: List<DrumModel>) {
        drumModelList = listOfDrumModel
    }
}