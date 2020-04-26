package com.yichenwu.doordashlite.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.yichenwu.doordashlite.R
import kotlinx.android.synthetic.main.restaurant_card.view.*

/*
    Custom view class for restaurant card
 */
class RestaurantCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.restaurant_card, this)
        if (layoutParams == null) {
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
        val paddings = resources.getDimensionPixelOffset(R.dimen.padding)
        setPadding(paddings, paddings, paddings, paddings)
        setBackgroundResource(R.color.white)
    }

    fun showImage(imageUrl: String? = null) {
        Glide
            .with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(image)
    }

    fun showName(name: String) {
        nameText.text = name
    }

    fun showDescription(description: String) {
        descriptionText.text = description
        descriptionText.visibility = View.VISIBLE
    }

    fun hideDescription() {
        descriptionText.visibility = View.GONE
    }

    fun showStatus(status: String) {
        statusText.text = status
        statusText.visibility = View.VISIBLE
    }

    fun hideStatus() {
        statusText.visibility = View.GONE
    }
}
