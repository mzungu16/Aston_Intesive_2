package com.example.aston_lesson_2.data

import android.os.Parcelable
import android.view.View.BaseSavedState

class DrumState(
    private val savedState: Parcelable?,
    val dataList: List<Pair<String, String>>
) : BaseSavedState(savedState), Parcelable {
}