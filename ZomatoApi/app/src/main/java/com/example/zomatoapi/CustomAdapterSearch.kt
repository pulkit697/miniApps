package com.example.zomatoapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapi.data.GeoCode
import com.example.zomatoapi.data.NearbyRestaurant
import com.example.zomatoapi.data.Restaurant
import kotlinx.android.synthetic.main.list_item_layout.view.*

class CustomAdapterSearch(var list : List<Restaurant>):RecyclerView.Adapter<CustomAdapterSearch.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var str = list[position].restaurant.name + ", " + list[position].restaurant.location.city
        holder.itemView.tvListItem.text = str
    }

    override fun getItemCount(): Int {
        Log.d("flags","SIze of RecyclerView: ${list.size}")
        return list.size
    }

}