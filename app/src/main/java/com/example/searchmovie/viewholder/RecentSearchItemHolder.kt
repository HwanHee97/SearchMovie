package com.example.searchmovie.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.databinding.LayoutRecentSearchItemBinding
import com.example.searchmovie.viewmodel.MainViewModel

class RecentSearchItemHolder(private val binding: LayoutRecentSearchItemBinding, private val mainViewModel: MainViewModel):RecyclerView.ViewHolder(binding.root) {
    fun bindWithRecentSearch(item:String) {
        binding.recentSearchButtonItem.apply {
            text = item
            setOnClickListener {
                mainViewModel.setRescentSearchString(item)
            }
        }
    }

}