package com.example.searchmovie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovie.databinding.LayoutMovieItemBinding
import com.example.searchmovie.model.RpMovieData
import com.example.searchmovie.viewholder.MovieItemHolder
class MovieRecyclerViewAdapter (var movieData: RpMovieData,val context: Context):RecyclerView.Adapter<MovieItemHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder {
        val binding=LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieItemHolder(binding,context)
    }

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bindWithMovie(this.movieData.items[position])
    }

    override fun getItemCount(): Int {
        return this.movieData.items.size
    }
    fun notifyPhotoDataChange(data:RpMovieData){
        movieData=data
        notifyDataSetChanged()
    }
}