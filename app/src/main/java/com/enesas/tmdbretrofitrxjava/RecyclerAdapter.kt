package com.enesas.tmdbretrofitrxjava

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enesas.tmdbretrofitrxjava.databinding.RecyclerRowBinding

class RecyclerAdapter (val movieList: ArrayList<Result>) : RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>() {
    class MovieViewHolder (val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.recyclerRowTextView.text = movieList.get(position).title
    }

    fun updateMovieList(updatedMovieList : List<Result>){
        movieList.addAll(updatedMovieList)
        notifyDataSetChanged()
    }
}