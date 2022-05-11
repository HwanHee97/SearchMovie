package com.example.searchmovie.network



import com.example.searchmovie.model.RpMovieData
import com.example.searchmovie.utils.API
import com.example.searchmovie.utils.RESPONSE_STATUS
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }
    private val iRetrofit: IRetrofitNaverApi? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofitNaverApi::class.java)
    private lateinit var call: Call<RpMovieData>

    fun callMovieData(searchText:String,completion: (RESPONSE_STATUS, RpMovieData?) -> Unit) {

        call=iRetrofit?.searchMovie("8qhdA32cuaaz_6mU_BMI","W9ZeGqevgm",searchText).let { it } ?: return

        call.enqueue(object : retrofit2.Callback<RpMovieData> {
            override fun onResponse(call: Call<RpMovieData>, response: Response<RpMovieData>) {
                when(response.code()){
                    200->{
                        response.body()?.let {
                            if (it.items.isEmpty()) {
                                completion(RESPONSE_STATUS.NO_CONTENT, it)
                            } else {
                                completion(RESPONSE_STATUS.OKAY, it)
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<RpMovieData>, t: Throwable) {
                completion(RESPONSE_STATUS.FAIL,null)
            }
        })
    }

}