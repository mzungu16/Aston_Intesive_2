package com.example.aston_lesson_2.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.aston_lesson_2.R
import com.example.aston_lesson_2.data.DrumModel
import com.example.aston_lesson_2.domain.DrumInteractionInt
import com.example.aston_lesson_2.utils.dpToPx

class DrumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), DrumInteractionInt {

    companion object {
        private const val DRUM_KEY = "DrumKeyData"
        private const val CIRCLE_WIDTH_PERCENT = 0.50
        const val DEFAULT_VIEW_SIZE_HEIGHT = 150
        const val DEFAULT_VIEW_SIZE_WIDTH = 250
    }

    private var circleRect = RectF()
    private var circleStrokeWidth = context.dpToPx(6)
    private var circleRadius = 0F
    private var circlePadding = context.dpToPx(8)
    private var circlePaintRoundSize = true
    private var circleSectionSpace = 3F
    private var circleCenterX = 0F
    private var circleCenterY = 0F
    private var drumColors = listOf<String>()
    private var percentageCircleList = listOf<DrumModel>()
    private var dataList = listOf<Pair<Int, String>>()
    private var animationSweepAngle = 0
    private var totalAmount: Int = 0


    init {
        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.DrumView)
            val colorResId = typeArray.getResourceId(R.styleable.DrumView_drumChartColors, 0)
            drumColors = typeArray.resources.getStringArray(colorResId).toList()

            circleStrokeWidth = typeArray.getDimension(
                R.styleable.DrumView_drumChartCircleStrokeWidth,
                circleStrokeWidth
            )
            circlePadding =
                typeArray.getDimension(R.styleable.DrumView_drumChartCirclePadding, circlePadding)
            circlePaintRoundSize = typeArray.getBoolean(
                R.styleable.DrumView_drumChartCirclePaintRoundSize,
                circlePaintRoundSize
            )
            circleSectionSpace = typeArray.getFloat(
                R.styleable.DrumView_drumChartCircleSectionSpace,
                circleSectionSpace
            )
            typeArray.recycle()
        }
        circlePadding += circleStrokeWidth
    }

    private fun resolveDefaultSize(spec: Int, defValue: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(defValue).toInt()
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun calculateViewHeight(heightMeasureSpec: Int): Int {
        return resolveDefaultSize(heightMeasureSpec, DEFAULT_VIEW_SIZE_HEIGHT)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val initSizeWidth = resolveDefaultSize(widthMeasureSpec, DEFAULT_VIEW_SIZE_WIDTH)
        val initSizeHeight = calculateViewHeight(heightMeasureSpec)
        calculateCircleRadius(initSizeWidth, initSizeHeight)

        setMeasuredDimension(initSizeWidth, initSizeHeight)
    }

    private fun calculateCircleRadius(width: Int, height: Int) {
        val circleViewWidth = (width * CIRCLE_WIDTH_PERCENT)
        circleRadius = if (circleViewWidth > height) {
            (height.toFloat() - circlePadding) / 2
        } else {
            circleViewWidth.toFloat() / 2
        }

        with(circleRect) {
            left = circlePadding
            top = height / 2 - circleRadius
            right = circleRadius * 2 + circlePadding
            bottom = height / 2 + circleRadius
        }

        circleCenterX = (circleRadius * 2 + circlePadding + circlePadding) / 2
        circleCenterY = (height / 2 + circleRadius + (height / 2 - circleRadius)) / 2
    }

    override fun setDataToView(list: List<Pair<Int, String>>) {
        dataList = list
        calculatePercentageOfData()
    }

    private fun calculatePercentageOfData() {
        totalAmount = dataList.fold(0) { res, value -> res + value.first}

        var startAt = circleSectionSpace
        percentageCircleList = dataList.mapIndexed { index, pair ->
            var percent = pair.first * 100 / totalAmount.toFloat() - circleSectionSpace
            percent = if (percent < 0F) 0F else percent

            val resultModel = DrumModel(
                percentOfCircle = percent,
                percentToStartAt = startAt,
                colorOfLine = Color.parseColor(drumColors[index % drumColors.size]),
                stroke = circleStrokeWidth,
                paintRound = circlePaintRoundSize
            )
            if (percent != 0F) startAt += percent + circleSectionSpace
            resultModel
        }
    }

    override fun startAnimation() {
        val animator = ValueAnimator.ofInt(0, 360).apply {
            duration = 1500
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener { valueAnimator ->
                animationSweepAngle = valueAnimator.animatedValue as Int
                invalidate()
            }
        }
        animator.start()
    }

    private fun drawDrum(canvas: Canvas) {
        for (percent in percentageCircleList) {
            if (animationSweepAngle > percent.percentToStartAt + percent.percentOfCircle) {
                canvas.drawArc(
                    circleRect,
                    percent.percentToStartAt,
                    percent.percentOfCircle,
                    false,
                    percent.paint
                )
            } else if (animationSweepAngle > percent.percentToStartAt) {
                canvas.drawArc(
                    circleRect,
                    percent.percentToStartAt,
                    animationSweepAngle - percent.percentToStartAt,
                    false,
                    percent.paint
                )
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawDrum(canvas)
    }
}