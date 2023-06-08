package com.beststreaming.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beststreaming.databinding.ActivityItemBinding
import com.beststreaming.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: ArrayList<Movie>
) : RecyclerView.Adapter<MovieAdapter.DataViewHolder>() {

    /**
     * Inflate activity_item.xml
     * @return RecyclerView binding to activity_item.xml whit data.
     */
    class DataViewHolder(private val binding: ActivityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(binding.root).load(movie.posterUrl).into(binding.ivMovie)
            binding.txtName.text = movie.name
            binding.txtDescription.text = movie.playtime
        }
    }

    /**
    * Inflate activity_item.xml
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder  = DataViewHolder(
        ActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun addData(list: List<Movie>) {
        movies.addAll(list)
    }
}