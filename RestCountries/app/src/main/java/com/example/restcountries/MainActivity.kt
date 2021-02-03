package com.example.restcountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountries.api.Client
import com.example.restcountries.data.RegionResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var list = RegionResult()
    val adapter= CustomAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        fetchData()
    }
    fun fetchData()
    {
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) { Client.api.getCountries("asia").execute() }
            if (response.isSuccessful)
            {
                list = response.body()!!
                Log.d("pulkit",list[0].name)
                refreshList()
            }
            else
            {
                tvLoading.visibility=View.VISIBLE
                rvList.visibility = View.GONE
                tvLoading.text = "Error"
            }
        }
    }
    fun refreshList()
    {
        tvLoading.visibility=View.GONE
        rvList.visibility = View.VISIBLE
        adapter.setListValue(list)
        adapter.notifyDataSetChanged()
    }
}