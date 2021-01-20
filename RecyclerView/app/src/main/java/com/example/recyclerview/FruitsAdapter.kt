package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_fruit.view.*

class FruitsAdapter(var list:ArrayList<Fruit>):RecyclerView.Adapter<FruitsAdapter.FruitsViewHolder>() {

    inner class FruitsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_fruit,parent,false)
        return FruitsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FruitsViewHolder, position: Int) {
        holder.itemView.tvName.text = list[position].name
        holder.itemView.tvLocation.text = list[position].location
        holder.itemView.tvQuantity.text = list[position].quantity.toString()
    }

    override fun getItemCount(): Int = list.size
}