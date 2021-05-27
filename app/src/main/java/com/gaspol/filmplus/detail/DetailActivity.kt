package com.gaspol.filmplus.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gaspol.filmplus.R
import com.gaspol.filmplus.core.ui.model.MoviePresentation
import com.gaspol.filmplus.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<MoviePresentation>(EXTRA_DATA)
        populateDetailMovie(detailMovie)
    }

    private fun populateDetailMovie(detailMovie: MoviePresentation?){
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.content.tvUserScore.text = detailMovie.voteAverage.toString()
            binding.content.tvOverview.text = detailMovie.overview
            binding.content.tvReleaseDate.text = detailMovie.releaseDate

            if (detailMovie.backdropPath == null){
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/original${detailMovie.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error_image)
                    .into(binding.ivPoster)
            } else {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/original${detailMovie.backdropPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error_image)
                    .into(binding.ivPoster)
            }

            var favoriteState = detailMovie.favorite
            setFavoriteState(favoriteState)
            binding.fab.setOnClickListener {
                favoriteState = !favoriteState
                detailViewModel.setFavoriteMovie(detailMovie, favoriteState)
                setFavoriteState(favoriteState)
            }
        }
    }

    private fun setFavoriteState(favoriteState : Boolean){
        if (favoriteState) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border))
        }
    }
}