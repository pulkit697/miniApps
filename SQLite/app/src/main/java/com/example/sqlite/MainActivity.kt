package com.example.sqlite

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sqlite.db.DatabaseHelper
import com.example.sqlite.db.TodoTable
import com.example.sqlite.model.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val todos = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        val db = DatabaseHelper(this).writableDatabase

        val todoAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                todos
        )

        fun refreshTodoList()
        {
            todos.clear()
            val list = TodoTable.getAllToDo(db)
            todos.addAll(list)
            todoAdapter.notifyDataSetChanged()
        }

        lvTodo.adapter = todoAdapter
        refreshTodoList()



        btTodo.setOnClickListener {
            val s = etTodo.text.toString()
            if(s.isNotEmpty()){
                val todo = Todo(s,false)
                TodoTable.insertToDo(db, todo)
                refreshTodoList()
                etTodo.text.clear()
                hideKeyboard(this,currentFocus?:btTodo)
//                todos.add(s)
//                todoAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"Please enter some task to do!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun hideKeyboard(activity: Activity, view: View)
    {
//        Log.d("pulkit",view.toString())
        (activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken,0)
    }
}