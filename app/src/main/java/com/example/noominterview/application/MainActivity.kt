package com.example.noominterview.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noominterview.databinding.ActivityMainBinding

// MainActivity intended to handle only app-level responsibilities - startup/navigation
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}