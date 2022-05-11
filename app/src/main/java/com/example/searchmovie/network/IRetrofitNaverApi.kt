package com.example.searchmovie.network

import com.example.searchmovie.model.RpMovieData
import com.example.searchmovie.utils.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface IRetrofitNaverApi {
    @GET(API.MOVIE_SEARCH)
    fun searchMovie(
        @Header("X-Naver-Client-Id")clientId:String,
        @Header("X-Naver-Client-Secret")clientSecret:String,
        @Query("query") searchText:String
        ): Call<RpMovieData>
}