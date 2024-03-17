package com.nyandori.quoteease

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyandori.quoteease.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {
    companion object{
        var CalculationResults = 0
    }
    lateinit var binding: ActivityResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityResultsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.testResults.text = CalculationResults.toString()
    }
}