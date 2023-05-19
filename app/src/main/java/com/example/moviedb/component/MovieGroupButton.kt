package com.example.moviedb.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.moviedb.R
import com.example.moviedb.databinding.ComponentMovieGroupButtonBinding

class MovieGroupButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyleRes){

    private val binding = ComponentMovieGroupButtonBinding.inflate(LayoutInflater.from(context), this, true)

    var onLeftButtonClick: (() -> Unit)? = null

    var onRightButtonClick: (() -> Unit)? = null

    var leftButtonText: String? = null
        set(value) {
            field = value
            setupBodyLeftButton(value)
        }

    var rightButtonText: String? = null
        set(value) {
            field = value
            setupBodyRightButton(value)
        }

    init {
        attrs?.let(::fillAttributes)
    }

    private fun fillAttributes(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.MovieGroupButton
        )
        leftButtonText = attributes.getString(R.styleable.MovieGroupButton_left_field_name)
        rightButtonText = attributes.getString(R.styleable.MovieGroupButton_right_field_name)
    }

    private fun setupBodyLeftButton(text: String?) = with(binding){
        leftButton.text = text
        leftButton.setOnClickListener {
            onLeftButtonClick?.invoke()
        }
    }

    private fun setupBodyRightButton(text: String?) = with(binding){
        rightButton.text = text
        rightButton.setOnClickListener {
            onRightButtonClick?.invoke()
        }
    }
}