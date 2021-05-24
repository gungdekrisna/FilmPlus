package com.gaspol.filmplus.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gaspol.filmplus.core.domain.usecase.FilmUseCase

class HomeViewModel(filmUseCase: FilmUseCase) : ViewModel() {
    val movies = filmUseCase.getAllMovies().asLiveData()
}