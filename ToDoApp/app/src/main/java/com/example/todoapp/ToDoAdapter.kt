package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.db.ToDo
import kotlinx.android.synthetic.main.layout_list_item_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class ToDoAdapter(private val listOfTodos: ArrayList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item_view,parent,false)
        return ToDoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.itemView.apply{
            tvTitle.text = listOfTodos[position].title
            tvDesc.text = listOfTodos[position].description
            tvCategory.text = listOfTodos[position].category
            setDate(holder,listOfTodos[position].date)
            setTime(holder,listOfTodos[position].time)
            holder.itemView.colored_card.setCardBackgroundColor(Color.argb(Random.nextInt(128)+127,Random.nextInt(255),Random.nextInt(255),Random.nextInt(255)))
        }
    }

    override fun getItemId(position: Int): Long {
        return listOfTodos[position].id
    }

    private fun setDate(holder: ToDoViewHolder, date: Long) {
        val format = "d-MM-yyyy"
        val s = SimpleDateFormat(format)
        holder.itemView.tvDate.text = s.format(Date(date))
    }

    private fun setTime(holder: ToDoAdapter.ToDoViewHolder, time: Long) {
        val format = "h:mm aa"
        val s = SimpleDateFormat(format)
        holder.itemView.tvTime.text = s.format(Date(time))
    }

    override fun getItemCount(): Int = listOfTodos.size
}
