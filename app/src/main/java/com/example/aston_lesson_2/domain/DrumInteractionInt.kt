package com.example.aston_lesson_2.domain

import com.example.aston_lesson_2.data.DrumModel

interface DrumInteractionInt {
    fun setData(listOfDrumModel: List<DrumModel>)
}