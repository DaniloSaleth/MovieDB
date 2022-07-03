package com.example.citmoviedatabase.ui.movieDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase.R
import com.example.citmoviedatabase.model.Image.Backdrops

class ImageAdapter(private val images : ArrayList<Backdrops>, private val type : String) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){
    inner class ImageViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageBackdrops)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        var response = ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
        when(type){
            "detail" ->{
                response = ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
            }
            "dialog" ->{
                response = ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item_dialog, parent, false))
            }
        }
        return response
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val bannerUrl = "https://image.tmdb.org/t/p/w500" + images[position].file_path
        Glide.with(holder.image)
            .load(bannerUrl)
            .fitCenter()
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}