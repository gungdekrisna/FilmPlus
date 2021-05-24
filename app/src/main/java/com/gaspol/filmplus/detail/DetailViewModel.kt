package com.gaspol.filmplus.detail

import androidx.lifecycle.ViewModel
import com.gaspol.filmplus.core.domain.usecase.FilmUseCase
import com.gaspol.filmplus.core.ui.model.MoviePresentation
import com.gaspol.filmplus.core.ui.utils.DataMapper

class DetailViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: MoviePresentation, newState: Boolean) {
        val movieDomain = DataMapper.mapPresentationToDomain(movie)
        filmUseCase.setFavoriteMovie(movieDomain, newState)
    }
}