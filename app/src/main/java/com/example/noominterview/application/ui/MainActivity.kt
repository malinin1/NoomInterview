package com.example.noominterview.application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noominterview.application.NoomApplication
import com.example.noominterview.databinding.ActivityMainBinding
import com.example.noominterview.util.ViewModelFactory
import javax.inject.Inject

// MainActivity intended to handle only app-level responsibilities - startup/navigation
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainActivityViewModel>

    private val viewModel by lazy {
        viewModelFactory.get<MainActivityViewModel>(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        NoomApplication.getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}