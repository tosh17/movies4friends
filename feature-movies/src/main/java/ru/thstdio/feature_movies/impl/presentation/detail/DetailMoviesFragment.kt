package ru.thstdio.feature_movies.impl.presentation.detail

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.DetailMoviesFragmentBinding

class DetailMoviesFragment : Fragment(R.layout.detail_movies_fragment) {
    val binding: DetailMoviesFragmentBinding by viewBinding(DetailMoviesFragmentBinding::bind)
}