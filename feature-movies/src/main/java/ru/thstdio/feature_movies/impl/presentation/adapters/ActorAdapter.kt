package ru.thstdio.feature_movies.impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.thstdio.core_data.domain.Actor
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.ActorViewHolderBinding

class ActorAdapter() :
    RecyclerView.Adapter<ActorHolder>() {
    private var actors: List<Actor> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.actor_view_holder, parent, false)
        return ActorHolder(view)
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.onBindView(actors[position])
    }

    override fun getItemCount() = actors.size
    fun setActors(actors: List<Actor>) {
        this.actors = actors
        notifyDataSetChanged()
    }
}

class ActorHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ActorViewHolderBinding.bind(view)
    fun onBindView(actor: Actor) {
        setName(actor.name)
        setImg(actor.picture)
    }

    private fun setImg(picture: String) {
        binding.imageMovie.load(picture) {
            placeholder(R.drawable.ic_actor)
            crossfade(true)
            transformations(
                RoundedCornersTransformation(24f, 24f, 24f, 24f)
            )
        }
    }

    private fun setName(name: String) {
        binding.textMove.text = name
    }
}