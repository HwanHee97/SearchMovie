package com.example.searchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.searchmovie.databinding.ActivityMainBinding
import com.example.searchmovie.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setContentView(binding.root)
        setButtonClickListener()
    }
    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }
    private fun setButtonClickListener(){
        binding.apply {
            searchButton.setOnClickListener {
                viewModel.getMovieData(etSearch.text.toString())
            }
            recentSearchButton.setOnClickListener {

            }
        }
    }
}