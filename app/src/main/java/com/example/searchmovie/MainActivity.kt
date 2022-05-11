package com.example.searchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchmovie.databinding.ActivityMainBinding
import com.example.searchmovie.model.MovieItem
import com.example.searchmovie.model.RpMovieData
import com.example.searchmovie.utils.Constants
import com.example.searchmovie.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var rpMovieData: RpMovieData=RpMovieData(listOf<MovieItem>())
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private  val movieRecyclerViewAdapter:MovieRecyclerViewAdapter by lazy {
        MovieRecyclerViewAdapter(rpMovieData,this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setContentView(binding.root)
        setButtonClickListener()
        setRecyclerView()
        setObserve()
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
    private fun setRecyclerView() {
        binding.recyclerMovieItem.apply {
            layoutManager= LinearLayoutManager(this.context)
            adapter=movieRecyclerViewAdapter
        }
    }
    private fun setObserve() {
        viewModel.rpMovieData.observe(this, Observer {
            Log.d(Constants.TAG, "MainActivity - setObserve: ")
            movieRecyclerViewAdapter.notifyPhotoDataChange(it)
        })
    }

}