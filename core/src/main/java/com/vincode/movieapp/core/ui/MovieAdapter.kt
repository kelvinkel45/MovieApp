package com.vincode.movieapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vincode.movieapp.core.R
import com.vincode.movieapp.core.domain.model.Movie
import kotlinx.android.synthetic.main.item_movies.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder> (){

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false))


    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movie) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185/" + data.posterPath)
                    .into(img_item_movie)
                tv_item_title.text = data.title
                if (data.releaseDate != ""){
                    tv_item_year.text = data.releaseDate?.substring(0,4)
                }else{
                    tv_item_year.text = "N/A"
                }

                rb_item_rating.rating = data.voteAverage / 2
                tv_item_rating.text = data.voteAverage.toString()
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}


