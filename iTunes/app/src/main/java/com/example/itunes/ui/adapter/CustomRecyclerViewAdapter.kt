package com.example.itunes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itunes.R
import com.example.itunes.data.model.SingleTrack
import kotlinx.android.synthetic.main.layout_single_track.view.*

class CustomRecyclerViewAdapter(private val tracksList: List<SingleTrack>):
    RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewHolder>() {
    class CustomRecyclerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(track:SingleTrack) = itemView.apply {
            tvTrackName.isSelected = true
            tvTrackName.text = track.trackName
            tvArtistName.text = track.artistName
            Glide.with(this).load(track.artworkUrl60).into(this.ivTrackImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_single_track,parent,false))

    override fun onBindViewHolder(holder: CustomRecyclerViewHolder, position: Int)  { holder.bind(tracksList[position])}

    override fun getItemCount() = tracksList.size
}