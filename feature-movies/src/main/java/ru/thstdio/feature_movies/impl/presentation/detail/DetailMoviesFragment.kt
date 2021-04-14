package ru.thstdio.feature_movies.impl.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.BlurTransformation
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.thstdio.core_data.domain.Actor
import ru.thstdio.core_data.domain.MOVIES_ID
import ru.thstdio.core_data.domain.MoviesDetail
import ru.thstdio.core_ui_util.base.BaseFragment
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.DetailMoviesFragmentBinding
import ru.thstdio.feature_movies.impl.framework.di.MoviesFeatureComponentHolder
import ru.thstdio.feature_movies.impl.presentation.adapters.ActorAdapter
import javax.inject.Inject

internal class DetailMoviesFragment : BaseFragment(R.layout.detail_movies_fragment) {
    private val binding: DetailMoviesFragmentBinding by viewBinding(DetailMoviesFragmentBinding::bind)

    @Inject
    lateinit var viewModelFactory: DetailMoviesModelViewFactory
    private val viewModel: DetailMoviesModelView by lazy {
        ViewModelProvider(this, viewModelFactory).get(DetailMoviesModelView::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesFeatureComponentHolder.getComponent().inject(this)
        arguments?.let { arg ->
            viewModel.updateMovie(arg.getLong(MOVIES_ID))
        }
        binding.areaBack.setOnClickListener {
            onBack()
        }

        viewModel.moviesDetail
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .filter { it != null }
            .onEach { movies ->
                bindView(movies!!)
            }
            .launchIn(lifecycleScope)
        val adapter = ActorAdapter()
        binding.recyclerView.adapter = adapter

//        ViewCompat.setTransitionName(binding.detailContainer, SHARED_VIEW_CINEMA_DETAIL)
    }

    private fun bindView(movies: MoviesDetail) {
        binding.textTitle.text = movies.title
        setBg(movies.backdrop)
        binding.textTag.text = movies.genres.joinToString { it.name }
        binding.textReviewsImdb.text = getString(R.string.review_string, movies.numberOfRatings)
        binding.textStory.text = movies.overview
        binding.ratingImdb.setRating(movies.ratings)
        showActorList(movies.actors)
    }

    private fun setBg(poster: String) {
        binding.imageBgOrig.load(poster) {
            crossfade(true)
            placeholder(R.drawable.cinema_holder)
            transformations(
                listOf(
                    //GrayscaleTransformation(),
                    BlurTransformation(requireContext(), 5f)
                )
            )
        }
    }

    private fun showActorList(actors: List<Actor>) {
        val adapter = binding.recyclerView.adapter as? ActorAdapter
        adapter?.setActors(actors)
        binding.textCast.isVisible = actors.isNotEmpty()
        binding.recyclerView.isVisible = actors.isNotEmpty()
    }
}