package com.example.aston_lesson_2

import android.os.Bundle
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
            drumView.setDataToView(
                listOf(Pair(4, "Свои проекты"), Pair(6, "Соместные проекты"), Pair(6, "Проекты поддержанные группой людей"), Pair(2, "Неизвестные проекты"))
            )
        }
    }
}