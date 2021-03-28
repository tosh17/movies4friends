package ru.thstdio.feature_movies.impl.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.MoviesListFragmentBinding
import ru.thstdio.feature_movies.impl.framework.di.MoviesFeatureComponentHolder
import javax.inject.Inject

class MoviesListFragment : Fragment(R.layout.movies_list_fragment) {
    @Inject
    lateinit var viewModelFactory: MoviesListViewModelFactory
    private val viewModel: MoviesListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MoviesListViewModel::class.java)
    }
    private val binding: MoviesListFragmentBinding by viewBinding(MoviesListFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesFeatureComponentHolder.getComponent().inject(this)
    }
}