package com.example.firebasetutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.note_layout.view.*

class CustomAdapter(val _context: Context,val resourceLayout:Int,val notesList:ArrayList<Note>): ArrayAdapter<Note>(_context,resourceLayout,notesList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view==null)
        {
            view = LayoutInflater.from(parent.context).inflate(resourceLayout,parent,false)
        }
        view!!.apply {
            tvTitle.text = notesList[position].title
            tvMessage.text = notesList[position].message
        }
        return view
    }
}