package com.example.todoapp

import android.content.Intent
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.db.ToDo
import com.example.todoapp.db.TodoDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var listOfTodos = arrayListOf<ToDo>()
    val db by lazy {
        TodoDatabase.getInstance(this)
    }
    val adapter = ToDoAdapter(listOfTodos)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI
        setSupportActionBar(toolBar)
        initialiseSwipe()
        btFab.setOnClickListener { Intent(this,NewTaskActivity::class.java).apply { startActivity(this) } }

        //recycler view set up
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = this.adapter

        //fetching list
        db.getDao().getIncompleteTasks().observe(this, {
            if(it!=null)
            {
                listOfTodos.clear()
                listOfTodos.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun initialiseSwipe()
    {
        val simpleItemTouchCallback = object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dx: Float, dy: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val itemView = viewHolder.itemView
                val paint = Paint()
                val icon:Bitmap
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
                {
                    //right swipe // finish
                    if(dx>0)
                    {
                        paint.color = Color.GREEN
                        icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_check_white)!!.toBitmap()
                        canvas.drawRect(itemView.left.toFloat(),itemView.top.toFloat(),itemView.left.toFloat()+dx,itemView.bottom.toFloat(),paint)
                        val diff = itemView.bottom.toFloat()-itemView.top.toFloat()-icon.height.toFloat()
                        canvas.drawBitmap(icon,itemView.left.toFloat(),diff/2,paint)
                        itemView.translationX = dx
                    }
                    else
                    {
                        paint.color = Color.RED
//                        icon = BitmapFactory.decodeResource(resources,R.drawable.ic_check_white)
                        icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_delete_white)!!.toBitmap()
                        canvas.drawRect(itemView.right.toFloat()+dx,itemView.top.toFloat(),itemView.right.toFloat(),itemView.bottom.toFloat(),paint)
                        val diff = itemView.bottom.toFloat()-itemView.top.toFloat()-icon.height.toFloat()
                        canvas.drawBitmap(icon,itemView.right.toFloat()-icon.width,diff/2,paint)
                        itemView.translationX = dx
                    }
                }
                else
                {
                    super.onChildDraw(canvas, recyclerView, viewHolder, dx, dy, actionState, isCurrentlyActive)
                }
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(direction == ItemTouchHelper.LEFT)
                {
                    GlobalScope.launch(Dispatchers.IO) {
                        db.getDao().delete(adapter.getItemId(position))
                    }
                }
                else if(direction == ItemTouchHelper.RIGHT)
                {
                    GlobalScope.launch(Dispatchers.IO) {
                        db.getDao().finishTask(adapter.getItemId(position))
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        //search icon
        setUpSearchIcon(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpSearchIcon(menu: Menu) {
        val searchView = menu.findItem(R.id.menuSearch).actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(!newText.isNullOrEmpty()) {
                    displayQueryList(newText)
                }
                return true
            }

        })
    }

    private fun displayQueryList(query: String="") {
        db.getDao().getIncompleteTasks().observe(this,{
                if (!it.isNullOrEmpty())
                {
                    listOfTodos.clear()
                    listOfTodos.addAll(it.filter { toDo ->
                        toDo.title.contains(query,true)
                    })
                    adapter.notifyDataSetChanged()
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menuHistory -> startActivity(Intent(this,HistoryActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}