package com.gaspol.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gaspol.filmplus.core.data.FilmRepository
import com.gaspol.filmplus.core.domain.usecase.FilmUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var filmRepository: FilmRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmUseCase: FilmUseCase

    @Before
    fun setUp() {
    }

    fun testGetFavoriteMovies() {

    }
}