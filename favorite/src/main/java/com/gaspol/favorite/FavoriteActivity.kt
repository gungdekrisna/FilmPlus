package com.gaspol.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspol.favorite.databinding.ActivityFavoriteBinding
import com.gaspol.favorite.di.favoriteModule
import com.gaspol.filmplus.core.ui.MovieAdapter
import com.gaspol.filmplus.core.ui.utils.DataMapper
import com.gaspol.filmplus.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.title_favorite)

        loadKoinModules(favoriteModule)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedMovie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedMovie)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovies.observe(this, { movies ->
            val moviePresentation = DataMapper.mapDomainToPresentation(movies)
            movieAdapter.setMovies(moviePresentation)
            binding.imgEmptyIconFilm.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE
            binding.tvEmptyMovie.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })

        with(binding.rvFilm) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}