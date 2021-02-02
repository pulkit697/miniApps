package com.example.restcountries

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountries.data.RegionResult
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.list_item_layout.view.*

class CustomAdapter(var context:Context):RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    var list = RegionResult()

    fun setListValue(list: RegionResult)
    {
        this.list=list
    }

    inner class CustomViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout,parent,false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val country = list[position]
        val subRegion = "- "+ country.subregion
        val population = "Population: " + country.population
        var borders="Borders: "
        var languages = "Languages: "
        for(item in country.borders)
        {
            borders= "$borders$item, "
        }
        for(item in country.languages)
        {
            languages ="$languages${item.name}, "
        }
        borders = borders.subSequence(0,borders.length-2).toString()
        languages = languages.subSequence(0,languages.length-2).toString()
        holder.itemView.apply {
            tvCountry.text = country.name
            tvCapital.text = country.capital
            tvPopulation.text = population
            tvRegion.text = country.region
            tvSubRegion.text = subRegion
            tvBorders.text = borders
            tvLanguages.text = languages
        }
        Log.d("picasso",country.flag)
        loadImage(country.flag,holder.itemView.ivFlag)
    }

    override fun getItemCount(): Int = list.size

    private fun loadImage(url:String,view:ImageView)
    {
        GlideToVectorYou.init().with(context).load(Uri.parse(url),view)
    }
}