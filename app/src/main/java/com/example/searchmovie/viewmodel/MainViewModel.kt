package com.example.searchmovie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovie.model.RpMovieData
import com.example.searchmovie.network.RetrofitManager
import com.example.searchmovie.utils.Constants
import com.example.searchmovie.utils.RESPONSE_STATUS

class MainViewModel:ViewModel(){
    private var _movieData= MutableLiveData<RpMovieData>()
    val rpMovieData: LiveData<RpMovieData>
        get() =_movieData

    fun getMovieData(searchText: String) {
        RetrofitManager.instance.callMovieData(searchText,completion = {
                responseStatus, MovieData ->
            when (responseStatus) {
                RESPONSE_STATUS.OKAY -> {
                    if (MovieData != null) {
                        Log.d(Constants.TAG, "MainViewModel - api 호출 성공: ${MovieData}")
                        _movieData.value=MovieData
                    }
                }
                RESPONSE_STATUS.FAIL -> {
                    Log.d(Constants.TAG, "MainViewModel - api 호출 실패:")
                }
                RESPONSE_STATUS.NO_CONTENT -> {
                    Log.d(Constants.TAG, "MainViewModel - 검색 결과가 없습니다.")
                }
            }
        })
    }

}