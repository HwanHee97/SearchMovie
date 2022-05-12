package com.example.searchmovie.viewholder


import android.util.Log
import androidx.recyclerview.widget.RecyclerView

import com.example.searchmovie.databinding.LayoutRecentSearchItemBinding
import com.example.searchmovie.utils.Constants
import com.example.searchmovie.viewmodel.MainViewModel

class RecentSearchItemHolder(val binding: LayoutRecentSearchItemBinding,val mainViewModel: MainViewModel):RecyclerView.ViewHolder(binding.root) {
    fun bindWithRecentSearch(item:String) {
        binding.recentSearchButtonItem.apply {
            text = item
            setOnClickListener {
                mainViewModel.setRescentSearchString(item)
            }
        }
    }

}