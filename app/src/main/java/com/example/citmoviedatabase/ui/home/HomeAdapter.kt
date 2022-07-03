package com.example.citmoviedatabase.ui.home


import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase.R
import com.example.citmoviedatabase.databinding.MovieItemBinding
import com.example.citmoviedatabase.model.Genre.Genre
import com.example.citmoviedatabase.model.Movie.Movie
import com.example.citmoviedatabase.ui.movieDetail.MovieDetailActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeAdapter (private val movies: ArrayList<Movie>, private val listOfGenres : List<Genre>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var addFavorite: ((Movie) -> Unit)? = null
    private var removeFavorite: ((Movie) -> Unit)? = null

    var listFavorite : List<Movie> = listOf()

    class ViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bannerUrl = "https://image.tmdb.org/t/p/w500" + movies[position].poster_path
        with(holder) {
            binding.titleTextView.text = movies[position].title
            binding.releaseTextView.text = getDateRelease(movies[position].release_date)
            binding.rateTextView.text = "| ${movies[position].vote_average.toString()}"

            if (isInFavorite(movies[position].id)){
                binding.ivFavorite.setImageResource(R.drawable.favorite_empty_icon_24)
            }else{
                binding.ivFavorite.setImageResource(R.drawable.favorite_menu_icon_24)
            }

            if (movies[position].genre_ids?.size!! < 1) {
                binding.genreTextView.text = ""
            } else {
                getMovieGenre(movies[position].genre_ids!!, holder)
            }

            Glide.with(binding.posterImageView)
                .load(bannerUrl)
                .fitCenter()
                .into(binding.posterImageView)

            binding.ivFavorite.setOnClickListener {
                if (isInFavorite(movies[position].id)){
                    addFavorite?.let {
                        it(movies[position])
                        binding.ivFavorite.setImageResource(R.drawable.favorite_menu_icon_24)
                    }
                }else{
                    removeFavorite?.let {
                        it(movies[position])
                        binding.ivFavorite.setImageResource(R.drawable.favorite_empty_icon_24)
                    }
                }
            }

            binding.dataMovie.setOnClickListener {
                val bundle = Bundle()
                val intent = Intent(binding.root.context, MovieDetailActivity::class.java)
                    .putExtra("id", movies[position].id)
                startActivity(binding.root.context, intent, bundle)
            }
        }
    }

    private fun isInFavorite(movieId : Int) : Boolean{
        var response = listFavorite.filter { it.id == movieId}
        return response.isEmpty()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private fun getMovieGenre(genre_ids: List<Int>, holder : ViewHolder){
            val genre = listOfGenres?.filter { it.id == genre_ids[0] }
            holder.binding.genreTextView.text = genre?.get(0)?.name
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateRelease(date: String?) : String{
        if(date != null && date != "") {
            val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val timestamp =
                (SimpleDateFormat("yyyy-MM-dd").parse(date)?.time ?: 0) / 1000// timestamp in Long
            val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(timestamp))
            val dateTime = LocalDate.parse(timestampAsDateString, secondApiFormat)

            return "◉ "+ dateTime.year
        } else {
          return "◉"
        }
    }

    fun addToFavorites(listener: (Movie) -> Unit) {
        addFavorite = listener
    }

    fun removeFavorites(listener: (Movie) -> Unit){
        removeFavorite = listener
    }

}