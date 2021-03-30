package ru.thstdio.feature_movies.impl.presentation.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.*

import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.MoviesListFragmentBinding

import ru.thstdio.feature_movies.impl.framework.di.MoviesFeatureComponentHolder
import ru.thstdio.feature_movies.impl.presentation.view.MovieListAdapter
import javax.inject.Inject

internal class MoviesListFragment : Fragment(R.layout.movies_list_fragment) {
    @Inject
    lateinit var viewModelFactory: MoviesListViewModelFactory
    private val viewModel: MoviesListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MoviesListViewModel::class.java)
    }
    private val binding: MoviesListFragmentBinding by viewBinding(MoviesListFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesFeatureComponentHolder.getComponent().inject(this)
        val moviesAdapter = MovieListAdapter({ movie -> })
        binding.recyclerView.adapter = moviesAdapter
        viewModel.currentMoviesList
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach{
               Log.e("Test","List size ${it.size}")
                moviesAdapter.submitList(it)}
            .launchIn(lifecycleScope)

            }

}