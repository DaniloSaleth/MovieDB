package com.example.citmoviedatabase.ui.movieDetail

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.citmoviedatabase.databinding.ActivityMovieDetailsBinding
import com.example.citmoviedatabase.databinding.DetailDialogBinding
import com.example.citmoviedatabase.databinding.ErrorDialogBinding
import com.example.citmoviedatabase.model.Cast.Cast
import com.example.citmoviedatabase.model.Image.Backdrops
import com.example.citmoviedatabase.networking.NetworkStatusChecker
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.M)
class MovieDetailActivity : AppCompatActivity() {
    private var movieId : Int = 0
    private val viewModel : DetailViewModel by viewModel()

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var bindingCastDialog: DetailDialogBinding

    private val networkStatusChecker by lazy{
        NetworkStatusChecker(this.applicationContext?.getSystemService(ConnectivityManager::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        movieId = intent.getIntExtra("id", 0)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        bindingCastDialog = DetailDialogBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply { hide() }

        if (networkStatusChecker.hasInternetConnection()) {
            viewModel.getCast(movieId)
            viewModel.getImages(movieId)
            viewModel.getMovie(movieId)

            startObservers()

            binding.backArrow.setOnClickListener{
                finish()
            }
            binding.showCast.setOnClickListener {
                showDialog("cast")
            }
            binding.showPhotos.setOnClickListener {
                showDialog("photos")
            }
        } else{
            showError("Verifique sua conexão.")
        }
    }

    private fun showDialog(type : String) {
        val intent = Intent(this, DetailDialog::class.java)
            .putExtra("id", movieId)
            .putExtra("type", type)
        startActivity(intent)
    }

    private fun synopsisChecker(){
        if(binding.movieDetailsSynopsis.text.length < 200){
            binding.showSynopsis.text = ""
        }

        binding.showSynopsis.setOnClickListener{
            when(binding.showSynopsis.text){
                "Show More" ->{
                    binding.movieDetailsSynopsis.maxLines = 10
                    binding.showSynopsis.text = "Show Less"
                }

                "Show Less" ->{
                    binding.movieDetailsSynopsis.maxLines = 4
                    binding.showSynopsis.text = "Show More"
                }
            }
        }
    }

    private fun startObservers(){
        viewModel.currentImage.observe(this){
            val imageAdapter = ImageAdapter(it as ArrayList<Backdrops>,"detail")
            binding.imageRecyclerView.adapter = imageAdapter
        }

        viewModel.currentCast.observe(this){
            val castAdapter = CastAdapter(it as ArrayList<Cast>)
            binding.castRecyclerView.adapter = castAdapter
        }

        viewModel.currentMovie.observe(this){ movie ->
            binding.movieDetailsTitle.text = movie.title
            binding.movieDetailsRate.text = movie.vote_average.toString()
            binding.movieDetailsTime.text = " ${movie.runtime} min"
            binding.movieDetailsSynopsis.text = movie.overview
            synopsisChecker()

            val allGenres : ArrayList<String> = ArrayList()
            for (genre in movie.genres!!){
                allGenres.add(genre.name)
            }
            binding.movieDetailsGenres.text = allGenres.joinToString(",")
            Glide.with(binding.movieDetailsBanner)
                .load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path)
                .fitCenter()
                .into(binding.movieDetailsBanner)


            binding.detailAllContent.visibility = View.VISIBLE
            binding.placeholderDetail.visibility = View.GONE
        }

        viewModel.error.observe(this){
            showError(it.message)
        }
    }

    private fun showError(msg : String?){
        val dialog = BottomSheetDialog(this)
        val dialogBinding = ErrorDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.dialogMsg.text = msg
        if (!(dialog.isShowing)) {
            dialog.show()
        }
    }
}