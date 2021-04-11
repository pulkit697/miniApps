package com.example.covid_19tracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19tracker.R
import com.example.covid_19tracker.data.StatewiseItem
import kotlinx.android.synthetic.main.layout_state_data.view.*

class CustomAdapter(private val statesList : List<StatewiseItem>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(state:StatewiseItem)
        {
            itemView.apply {
                tvName.text = state.state
                tvActive.text = state.active
                tvConfirmed.text = state.confirmed
                tvDeceased.text = state.deaths
                tvRecovered.text = state.recovered
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder = CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_state_data,parent,false))


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(statesList[position])
    }

    override fun getItemCount(): Int = statesList.size
}