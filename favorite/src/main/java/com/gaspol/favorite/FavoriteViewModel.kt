package com.gaspol.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gaspol.filmplus.core.domain.usecase.FilmUseCase

class FavoriteViewModel(filmUseCase: FilmUseCase) : ViewModel() {

    val favoriteMovies = filmUseCase.getFavoriteMovies().asLiveData()
}