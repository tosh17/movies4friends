package ru.thstdio.feature_movies.impl.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.thstdio.core_data.domain.Movies

import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.ViewHolderMovieBinding

class MovieListAdapter(
    private val onClick: (Movies) -> Unit
) : ListAdapter<Movies, MoviesListHolder>(MoviesListAdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MoviesListHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MoviesListHolder, position: Int) {
        getItem(position).let { movie -> holder.onBindView(movie) }
    }
}

class MoviesListHolder(view: View, private val onClick: (Movies) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val binding = ViewHolderMovieBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun onBindView(movie: Movies) {
        binding.textMovieName.text = movie.title
        binding.textMovieType.text = movie.genres.joinToString { it.name }
        binding.imageBg.load(movie.poster) {
            placeholder(R.drawable.ic_film)
            error(R.drawable.ic_film)
            transformations(RoundedCornersTransformation(topLeft = 6f, topRight = 6f))
        }
        // binding.textAge.text = "${cinema.adult.adultToAge()}+"
        setLike(movie.ratings > 8)
        binding.rating.setRating(movie.ratings)
        binding.textReviews.text =
            binding.textReviews.context.getString(R.string.review_string, movie.numberOfRatings)
        binding.root.setOnClickListener { onClick(movie) }
    }

    private fun setLike(isLike: Boolean) {
        binding.imageLike.setImageResource(if (isLike) R.drawable.ic_like_on else R.drawable.ic_like_off)
    }


}

private class MoviesListAdapterDiffUtil : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean =
        oldItem == newItem
}