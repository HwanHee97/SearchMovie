package com.example.searchmovie

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.example.searchmovie.adapter.RecentSearchRecyclerViewAdapter
import com.example.searchmovie.databinding.DialogLayoutBinding
import com.example.searchmovie.model.RecentSearchData
import com.example.searchmovie.viewmodel.MainViewModel
import com.google.android.flexbox.FlexboxLayoutManager

class CustomDialog(private val context: Context, private val recentSearchData: RecentSearchData, private val mainViewModel: MainViewModel) {
    private val dialog=Dialog(context)
    private lateinit var binding:DialogLayoutBinding

    fun startDialog() {
        binding= DialogLayoutBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.setCanceledOnTouchOutside(true)//밖에 터치시 종료
        binding.recyclerRecentSearch.apply {
            layoutManager = FlexboxLayoutManager(context)
            adapter=RecentSearchRecyclerViewAdapter(recentSearchData,mainViewModel)

        }

        dialog.show()
    }

    fun endDialog(){

        dialog.dismiss()

    }



}