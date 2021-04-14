package ru.thstdio.feature_movies.impl.presentation.view.rating

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.databinding.ViewRatingBinding


class RatingView : LinearLayout {


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private var binding: ViewRatingBinding? = null
    private fun init(attrs: AttributeSet?, defStyle: Int) {
        inflate(context, R.layout.view_rating, this)
        binding = ViewRatingBinding.bind(rootView)
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.RatingView, defStyle, 0
        )
        val rating = a.getFloat(R.styleable.RatingView_rating, 0f)
        val isBig = a.getBoolean(R.styleable.RatingView_isBig, false)
        val clickable = a.getBoolean(R.styleable.RatingView_clickable, false)
        if (isBig) {
            val bigHeight = dpToPx(36)
            val bigWidth = dpToPx(42)
            binding?.imageStar1?.let {star->
                star.layoutParams.height = bigHeight
                star.layoutParams.width = bigWidth
                if (clickable) star.setOnClickListener { setRating(2f) }
            }
            binding?.imageStar2?.let {star->
                star.layoutParams.height = bigHeight
                star.layoutParams.width = bigWidth
                if (clickable) star.setOnClickListener { setRating(4f) }
            }
            binding?.imageStar3?.let {star->
                star.layoutParams.height = bigHeight
                star.layoutParams.width = bigWidth
                if (clickable) star.setOnClickListener { setRating(6f) }
            }
            binding?.imageStar4?.let {star->
                star.layoutParams.height = bigHeight
                star.layoutParams.width = bigWidth
                if (clickable) star.setOnClickListener { setRating(8f) }
            }
            binding?.imageStar5?.let {star->
                star.layoutParams.height = bigHeight
                star.layoutParams.width = bigWidth
                if (clickable) star.setOnClickListener { setRating(10f) }
            }
        }
        setRating(rating)
        a.recycle()
    }

    private val stars: List<ImageView>
        get() = listOfNotNull(
            binding?.imageStar1,
            binding?.imageStar2,
            binding?.imageStar3,
            binding?.imageStar4,
            binding?.imageStar5
        )


    fun setRating(rating: Float) {
        val countStart = rating.toInt() / 2
        @ColorInt val activeColor: Int = ContextCompat.getColor(context, R.color.star_enable)
        @ColorInt val disableColor: Int = ContextCompat.getColor(context, R.color.star_disable)
        stars.forEachIndexed { index, starView ->
            val color = if (index < countStart) activeColor else disableColor
            starView.setColorFilter(color)
        }
    }

    private fun dpToPx(dp: Int): Int {
        return dp * Resources.getSystem().displayMetrics.density.toInt()
    }
}