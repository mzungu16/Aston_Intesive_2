package com.example.aston_lesson_2

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
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
        binding.run {
            startBtn.setOnClickListener {
                val rotateClick = AnimationUtils.loadAnimation(
                    this@MainActivity,
                    R.anim.rotate_clock_wise
                )
                drumView.startAnimation(rotateClick)
            }
        }
    }
}