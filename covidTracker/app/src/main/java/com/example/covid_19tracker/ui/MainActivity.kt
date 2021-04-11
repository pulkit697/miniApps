package com.example.covid_19tracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19tracker.R
import com.example.covid_19tracker.data.StatewiseItem
import com.example.covid_19tracker.networking.Client
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_header_designer.*
import kotlinx.android.synthetic.main.layout_state_data.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var listOfStates:ArrayList<StatewiseItem> = arrayListOf()
    var adapter = CustomAdapter(listOfStates)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LayoutInflater.from(this).inflate(R.layout.layout_header_designer,headerView,true)
        rvStates.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        fetchData()
    }
    fun fetchData()
    {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){ Client.api.getResponseData() }
            if(response.isSuccessful)
            {
                response.body()?.statewise?.let {
                    listOfStates.clear()
                    listOfStates.addAll(it)
                    adapter.notifyDataSetChanged()
                    tvActiveHeader.text = it.get(0).active
                    tvRecoveredHeader.text = it.get(0).recovered
                    tvDeathHeader.text = it.get(0).deaths
                    tvConfirmedHeader.text = it.get(0).confirmed
                }

            }
        }
    }
}