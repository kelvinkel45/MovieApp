package com.vincode.movieapp.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincode.movieapp.R
import com.vincode.movieapp.core.data.Resource
import com.vincode.movieapp.core.ui.MovieAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import androidx.lifecycle.lifecycleScope
import com.vincode.movieapp.presentation.detail.DetailMovieActivity
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class SearchActivity : AppCompatActivity() {

    private val searchViewModel : SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        edt_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lifecycleScope.launch {
                    searchViewModel.queryChannel.send(p0.toString())
                }
                searchViewModel.searchMovies.observe(this@SearchActivity, { searchMovies ->
                    when (searchMovies){
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Error -> progress_bar.visibility = View.GONE
                        is Resource.Success ->{
                            progress_bar.visibility = View.GONE
                            movieAdapter.setData(searchMovies.data)
                        }
                    }

                })
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        with(rv_search_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}