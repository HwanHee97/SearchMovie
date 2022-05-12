package com.example.searchmovie.utils

object Constants {
    const val TAG:String="로그"
    const val SEARCH_LIST_SHARED_KEY:String="recent_search_list"
}
enum class RESPONSE_STATUS{
    OKAY,
    FAIL,
    NO_CONTENT
}
object API{
    const val BASE_URL:String="https://openapi.naver.com/v1/"
    const val MOVIE_SEARCH:String="search/movie.json"
}
