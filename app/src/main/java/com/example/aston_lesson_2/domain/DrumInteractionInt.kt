package com.example.aston_lesson_2.domain

interface DrumInteractionInt {
    fun setDataToView(list: List<Pair<Int, String>>)

    fun startAnimation()
}