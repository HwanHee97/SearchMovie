package com.example.searchmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.databinding.LayoutRecentSearchItemBinding
import com.example.searchmovie.model.RecentSearchData
import com.example.searchmovie.viewholder.RecentSearchItemHolder
import com.example.searchmovie.viewmodel.MainViewModel

class RecentSearchRecyclerViewAdapter(private var searchData: RecentSearchData, private val mainViewModel: MainViewModel):RecyclerView.Adapter<RecentSearchItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchItemHolder {
        val binding=LayoutRecentSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecentSearchItemHolder(binding,mainViewModel)
    }

    override fun onBindViewHolder(holder: RecentSearchItemHolder, position: Int) {
        holder.bindWithRecentSearch(this.searchData.searchDataList[position])
    }

    override fun getItemCount(): Int {
        return searchData.searchDataList.size
    }
}