package com.example.citmoviedatabase.ui.movieDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citmoviedatabase.R
import com.example.citmoviedatabase.model.Cast.Cast

class CastAdapter (private val casts : ArrayList<Cast>) : RecyclerView.Adapter<CastAdapter.CastViewHolder>(){

    inner class CastViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val profilePhoto : ImageView = itemView.findViewById(R.id.profilePhoto)
        val actorName : TextView = itemView.findViewById(R.id.actorName)
        val character : TextView = itemView.findViewById(R.id.character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cast_item, parent, false))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val bannerUrl = "https://image.tmdb.org/t/p/w500" + casts[position].profile_path
        Glide.with(holder.profilePhoto)
            .load(bannerUrl)
            .fitCenter()
            .into(holder.profilePhoto)
        holder.actorName.text = casts[position].name
        holder.character.text = casts[position].character
    }

    override fun getItemCount(): Int {
        return casts.size
    }
}