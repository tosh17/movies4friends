package ru.thstdio.feature_movies.impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.impl.domain.MoviesListCollection
import ru.thstdio.feature_movies.impl.presentation.view.MoviesListHolder

class TitleAdapter(
    private val onClick: (TitleItemView) -> Unit
) : ListAdapter<TitleItemView, TitleHolder>(TitleAdapterDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_title, parent, false)
        return TitleHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TitleHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

class TitleHolder(
    private val view: View,
    private val onClick: (TitleItemView) -> Unit
) :

    RecyclerView.ViewHolder(view) {
    fun onBind(item: TitleItemView) {
        with(view.findViewById(R.id.title) as TextView) {
            text = item.type.name
            setTextAppearance(
                if (item.isSelect) R.style.TextH1_Primary
                else R.style.TextH2_Primary
            )
            if (item.isSelect) textSize= 28f
            else textSize= 18f
            setOnClickListener { onClick(item) }
        }
    }
}

private class TitleAdapterDiffUtil : DiffUtil.ItemCallback<TitleItemView>() {
    override fun areItemsTheSame(oldItem: TitleItemView, newItem: TitleItemView): Boolean {
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(oldItem: TitleItemView, newItem: TitleItemView): Boolean {
        return oldItem == newItem
    }
}

data class TitleItemView(val type: MoviesListCollection, val isSelect: Boolean)
