package com.example.moviedb.component

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupBodyLeftButton(text: String?) = with(binding){
        leftButton.text = text
        leftButton.setOnClickListener {
            leftButton.setTextColor(context.getColor(R.color.white))
            rightButton.setTextColor(context.getColor(R.color.blue_dark_5))
            onLeftButtonClick?.invoke()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupBodyRightButton(text: String?) = with(binding){
        rightButton.text = text
        rightButton.setOnClickListener {
            leftButton.setTextColor(context.getColor(R.color.blue_dark_5))
            rightButton.setTextColor(context.getColor(R.color.white))
            onRightButtonClick?.invoke()
        }
    }
}