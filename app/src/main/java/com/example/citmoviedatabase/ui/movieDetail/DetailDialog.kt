package com.example.citmoviedatabase.ui.movieDetail

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.citmoviedatabase.databinding.DetailDialogBinding
import com.example.citmoviedatabase.databinding.ErrorDialogBinding
import com.example.citmoviedatabase.model.Cast.Cast
import com.example.citmoviedatabase.model.Image.Backdrops
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.M)
class DetailDialog : AppCompatActivity() {
    private val viewModel : DetailViewModel by viewModel()
    private lateinit var binding: DetailDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DetailDialogBinding.inflate(layoutInflater)
        val movieId = intent.getIntExtra("id", 0)
        val type = intent.getStringExtra("type")
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply { hide() }

        binding.backDialog.setOnClickListener{
            finish()
        }

        when(type){
            "cast" ->{
                binding.titleDialog.text = "Cast & Crew"
                viewModel.getCast(movieId)
            }
            "photos" ->{
                binding.titleDialog.text = "Photos"
                viewModel.getImages(movieId)
            }
        }
        startObserver()
    }

    private fun startObserver(){
        viewModel.currentCast.observe(this){
            val castAdapter = CastAdapter(it as ArrayList<Cast>)

            binding.dialogRecyclerView.adapter = castAdapter
        }

        viewModel.currentImage.observe(this){
            val imageAdapter = ImageAdapter(it as ArrayList<Backdrops>, "dialog")

            binding.dialogRecyclerView.adapter = imageAdapter
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