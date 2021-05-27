package com.gaspol.filmplus.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspol.filmplus.core.data.Resource
import com.gaspol.filmplus.core.ui.MovieAdapter
import com.gaspol.filmplus.core.ui.utils.DataMapper
import com.gaspol.filmplus.databinding.FragmentHomeBinding
import com.gaspol.filmplus.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            binding.btnSearch.setOnClickListener {
                val query = binding.etSearch.text.toString().trim()
                homeViewModel.movieSearch(query).observe(viewLifecycleOwner, { movies ->
                    if (movies != null) {
                        when (movies) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                val moviePresentation = DataMapper.mapDomainToPresentation(movies.data!!)
                                movieAdapter.setMovies(moviePresentation)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.imgEmptyIconFilm.visibility = View.VISIBLE
                                binding.tvEmptyMovie.visibility = View.VISIBLE
                            }
                        }
                    }
                })
            }

            homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val moviePresentation = DataMapper.mapDomainToPresentation(movies.data!!)
                            movieAdapter.setMovies(moviePresentation)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.imgEmptyIconFilm.visibility = View.VISIBLE
                            binding.tvEmptyMovie.visibility = View.VISIBLE
                        }
                    }
                }
            })

            with(binding.rvFilm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}