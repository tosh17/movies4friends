package ru.thstdio.feature_movies.impl.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        val moviesAdapter = MovieListAdapter { movie ->
            findNavController().navigate(R.id.action_moviesListFragment_to_detailMoviesFragment)
        }
        binding.recyclerViewList.adapter = moviesAdapter
        viewModel.currentMoviesList
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                moviesAdapter.submitList(it)
            }
            .launchIn(lifecycleScope)

        binding.tabLayoutTitle.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.selectListType(tab?.tag.toString())
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })


    }

}