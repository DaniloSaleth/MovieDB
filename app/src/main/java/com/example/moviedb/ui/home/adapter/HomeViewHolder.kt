package com.example.moviedb.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.databinding.ItemMovieListBinding
import com.example.moviedb.infrastructure.api.Constants.POSTER_PATH
import com.example.moviedb.model.movie.Movie

class HomeViewHolder(
    private val binding: ItemMovieListBinding,
    private val listener: HomeListener
) : ViewHolder(binding.root) {
    fun bind(item: Movie) = with(binding) {
        bindView(item)
        bindingClick(item)
    }

    private fun bindView(item: Movie) = with(binding) {
        Glide.with(backdrop)
            .load(POSTER_PATH + item.poster_path)
            .fitCenter()
            .into(backdrop)

        title.text = item.title
        //genre.text = item.genre
        description.text = root.context.resources.getString(R.string.movie_item_description).format(item.vote_average, item.release_date)
    }

    private fun ItemMovieListBinding.bindingClick(item: Movie) = with(binding) {
        root.setOnClickListener {
            listener.onClickItem(item)
        }
    }
}