package com.example.searchmovie.model

import com.google.gson.annotations.SerializedName
data class RpMovieData(
    @SerializedName("items")
    val items:List<MovieItem>
)
data class MovieItem(
    @SerializedName("title")
    val title:String,
    @SerializedName("pubDate")
    val pubDate:String,
    @SerializedName("userRating")
    val userRating: String,
    @SerializedName("link")
    val link: String
)
