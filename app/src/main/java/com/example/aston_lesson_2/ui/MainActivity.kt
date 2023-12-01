package com.example.aston_lesson_2.ui

import android.graphics.Color
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_lesson_2.R
import com.example.aston_lesson_2.data.DrumModel
import com.example.aston_lesson_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_clock_wise)
        binding.run {
            drumView.setData(
                listOf(
                    DrumModel("Red", Color.RED),
                    DrumModel("Orange", Color.parseColor("#FFA500")),
                    DrumModel("Yellow", Color.YELLOW),
                    DrumModel("Green", Color.GREEN),
                    DrumModel("Blue", Color.CYAN),
                    DrumModel("DBlue", Color.BLUE),
                    DrumModel("Purple", Color.MAGENTA),
                )
            )
            startBtn.setOnClickListener {
                drumView.startAnimation(rotate)
                resetBtn.startAnimation(rotate)
            }
        }
    }

    private fun calculateWinner() {
        //
    }
}