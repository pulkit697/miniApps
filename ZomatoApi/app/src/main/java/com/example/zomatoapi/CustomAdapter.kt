package com.example.zomatoapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_layout.view.*

//class CustomAdapter:RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
//
//    private var locationDetail: LocationDetail?
//
//    init {
//        locationDetail = null
//    }
//
//    fun setLocation(locationDet :LocationDetail)
//    {
//        locationDetail = locationDet
//    }
//
//
//    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false)
//        return ViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.tvListItem.text = locationDetail?.nearby_restaurants?.get(position)?.name
//    }
//
//    override fun getItemCount(): Int {
//        if(locationDetail==null)
//            return 0
//        return locationDetail?.nearby_restaurants!!.size
//    }
//}