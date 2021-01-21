package com.example.zomatoapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapi.data.GeoCode
import com.example.zomatoapi.data.NearbyRestaurant
import kotlinx.android.synthetic.main.list_item_layout.view.*

class CustomAdapter(var list : List<NearbyRestaurant>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvListItem.text = list[position].restaurant.name
    }

    override fun getItemCount(): Int = list.size
}