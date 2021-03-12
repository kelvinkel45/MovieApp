package com.vincode.movieapp.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincode.movieapp.core.ui.MovieAdapter
import com.vincode.movieapp.presentation.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = "Favorite Movie"

        loadKoinModules(favoriteModule)

        val favoriteAdapter = MovieAdapter()
        favoriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }


        favoriteViewModel.favoriteMovies.observe(this, { movies ->
            favoriteAdapter.setData(movies)
            tv_empty.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE
            img_empty.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE

        })

        with(rv_favorite_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }
}