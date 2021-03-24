package com.example.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import com.example.todoapp.db.ToDo
import com.example.todoapp.db.TodoDatabase
import kotlinx.android.synthetic.main.activity_new_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NewTaskActivity : AppCompatActivity(), View.OnClickListener {

    val db by lazy {
        TodoDatabase.getInstance(this)
    }

    private lateinit var myCalendar:Calendar
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener
    private val categoryList = arrayListOf("Banking","Business","Self study","School","Household","Meeting")
    private lateinit var adapter:ArrayAdapter<String>
    var finalDate = 0L
    var finalTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        setUpSpinner()
        etDate.setOnClickListener(this)
        etTime.setOnClickListener(this)
        ivAddCategory.setOnClickListener(this)
        ivAddOk.setOnClickListener(this)
        btSave.setOnClickListener(this)
    }

    private fun setUpSpinner() {
        adapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,categoryList)
        spCategory.adapter = adapter
    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.etDate -> {
                setupDateListener()
            }
            R.id.etTime -> {
                setupTimeListener()
            }
            R.id.ivAddCategory -> {
                askForNewCategory()
            }
            R.id.ivAddOk -> {
                addCategoryToSpinner()
            }
            R.id.btSave -> {
                addToDataBase()
            }
        }
    }

    private fun addToDataBase() {
        if(etTitle.text.toString().isNotEmpty() && etDescription.text.toString().isNotEmpty() && etTime.text.toString().isNotEmpty() && etDate.text.toString().isNotEmpty()) {
            GlobalScope.launch(Dispatchers.IO) {
                db.getDao().insert(ToDo(etTitle.text.toString(), etDescription.text.toString(), spCategory.selectedItem.toString(),
                        finalDate, finalTime, -1))
            }
            finish()
        }
        else
        {
            Toast.makeText(this,"Please enter all fields",Toast.LENGTH_SHORT).show()
        }
    }

    private fun askForNewCategory() {
        spCategory.visibility = View.GONE
        etAddCategory.visibility = View.VISIBLE
        ivAddCategory.visibility = View.GONE
        ivAddOk.visibility = View.VISIBLE
    }

    private fun addCategoryToSpinner() {
        if(etAddCategory.text.isEmpty()) {
            Toast.makeText(this,"Please enter a category",Toast.LENGTH_SHORT).show()
        } else {
            categoryList.add(etAddCategory.text.toString())
            adapter.notifyDataSetChanged()
            etAddCategory.text.clear()
            spCategory.setSelection(categoryList.size -1)
            spCategory.visibility = View.VISIBLE
            etAddCategory.visibility = View.GONE
            ivAddCategory.visibility = View.VISIBLE
            ivAddOk.visibility = View.GONE
        }
    }

    private fun setupDateListener() {
        myCalendar = Calendar.getInstance()
        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateDate()
        }
        val datePickerDialog = DatePickerDialog(this,dateSetListener,
        myCalendar.get(Calendar.YEAR),
        myCalendar.get(Calendar.MONTH),
        myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDate() {
        val myFormat = "EEE d MMM yyyy"
        val simpleDateFormat = SimpleDateFormat(myFormat,Locale.getDefault())
        finalDate = myCalendar.time.time
        etDate.setText(simpleDateFormat.format(myCalendar.time))
        inputLayTime.visibility = View.VISIBLE
    }

    private fun setupTimeListener() {
        timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            myCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            myCalendar.set(Calendar.MINUTE,minute)
            updateTime()
        }
        val timePickerDialog = TimePickerDialog(this,timeSetListener,
                                            myCalendar.get(Calendar.HOUR_OF_DAY),
                                            myCalendar.get(Calendar.MINUTE),
                                            false)
        timePickerDialog.show()
    }

    private fun updateTime() {
        val myTimeFormat = "h:mm aa"
        val simpleTimeFormat = SimpleDateFormat(myTimeFormat,Locale.getDefault())
        etTime.setText(simpleTimeFormat.format(myCalendar.time))
        finalTime = myCalendar.time.time
    }

}