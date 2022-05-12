package com.example.searchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
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
    private val sharedPreference=SharedPreference()

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
        //검색
        binding.apply {
            searchButton.setOnClickListener {
                var searchString=etSearch.text.toString()
                viewModel.getMovieData(searchString)
                sharedPreference.setRecentListPref(this@MainActivity,Constants.SEARCH_LIST_SHARED_KEY,searchString)
                val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(etSearch.windowToken,0)//키보드 내리기
            }
            //최근검색어
            recentSearchButton.setOnClickListener {
                var recentSearchData=RecentSearchData(sharedPreference.getRecentListPref(this@MainActivity,Constants.SEARCH_LIST_SHARED_KEY))//최근 검색 리스트 가져오기
                if (recentSearchData.searchDataList.size>0){//최근 검색어 리스트가 없으면 recentSearchData == [] 이기때문
                    recentSearchData.searchDataList.reverse()//가장 최근순으로 보여주기위해 배열을 뒤집는다.
                    dialog = CustomDialog(context = this@MainActivity,recentSearchData,mainViewModel = viewModel)
                    dialog.startDialog()
                }else{
                    Toast.makeText(this@MainActivity,"최근 검색어가 없습니다.",Toast.LENGTH_SHORT).show()
                }
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
        viewModel.apply {
            rpMovieData.observe(this@MainActivity, Observer {
                movieRecyclerViewAdapter.notifyPhotoDataChange(it)
            })

            recentSearchString.observe(this@MainActivity, Observer {
                binding.apply {
                    etSearch.setText(it)
                    viewModel.getMovieData(it)
                    sharedPreference.setRecentListPref(this@MainActivity,Constants.SEARCH_LIST_SHARED_KEY,it)
                }
                dialog.endDialog()
            })
        }
    }

}