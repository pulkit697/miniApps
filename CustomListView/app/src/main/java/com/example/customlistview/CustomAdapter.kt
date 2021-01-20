package com.example.customlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.graphics.createBitmap

class CustomAdapter(var context: Context,var persons: Array<Person>) : BaseAdapter() {
    override fun getCount(): Int {
        return persons.size
    }

    override fun getItem(position: Int): Person {
        return persons[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_person,parent,false)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvRole = itemView.findViewById<TextView>(R.id.tvRole)
        tvName.text = persons[position].name
        tvRole.text = persons[position].role
        return  itemView
    }
}