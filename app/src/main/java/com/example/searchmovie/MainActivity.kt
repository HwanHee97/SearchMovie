package com.example.searchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchmovie.adapter.MovieRecyclerViewAdapter
import com.example.searchmovie.databinding.ActivityMainBinding
import com.example.searchmovie.model.MovieItem
import com.example.searchmovie.model.RecentSearchData
import com.example.searchmovie.model.RpMovieData
import com.example.searchmovie.utils.Constants
import com.example.searchmovie.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var rpMovieData: RpMovieData=RpMovieData(listOf<MovieItem>())
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var dialog: CustomDialog
    private  val movieRecyclerViewAdapter: MovieRecyclerViewAdapter by lazy {
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
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(etSearch.windowToken,0)
            }
            //최근검색어 로부터 검색기능 테스트
            recentSearchButton.setOnClickListener {
                var recentSearchData=RecentSearchData(arrayListOf("아바타","공조","타자","가나다라마바사아자","기억"))
                dialog = CustomDialog(context = this@MainActivity,recentSearchData,mainViewModel = viewModel)
                dialog.startDialog()
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
        viewModel.recentSearchString.observe(this, Observer {
            binding.apply {
                etSearch.setText(it)
                viewModel.getMovieData(etSearch.text.toString())
            }
            dialog.endDialog()
        })
    }

}