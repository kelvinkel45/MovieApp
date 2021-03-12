package com.vincode.movieapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vincode.movieapp.R
import com.vincode.movieapp.core.domain.model.Movie
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "intent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)


        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        if (movie != null) {
            detailMovieViewModel.isFavorite(movie.movieId).observe(this,{ state->
                showDetailMovie(movie, state)
            })
            if (movie.releaseDate != ""){
                toolbar.title = movie.title + "(${movie.releaseDate?.substring(0,4)})"
            }else{
                toolbar.title = movie.title
            }
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    private fun showDetailMovie(data: Movie?, stateFavorite : Boolean) {
        if (data != null) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + data.backdropPath)
                .into(img_detail_banner)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185/" + data.posterPath)
                .into(img_detail_poster)
            rb_detail_rating.rating = data.voteAverage / 2
            tv_detail_rating.text = data.voteAverage.toString()
            tv_overview_detail.text = data.overview
            var statusFavorite = stateFavorite
            setStatusFavorite(statusFavorite)
            fab_favorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(data)
                setStatusFavorite(statusFavorite)
                if (statusFavorite){
                    Snackbar.make(fab_favorite, getString(R.string.text_movie_added), Snackbar.LENGTH_LONG).show()
                }else{
                    Snackbar.make(fab_favorite, getString(R.string.text_movie_deleted), Snackbar.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab_favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        }else{
            fab_favorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white))
        }
    }


}