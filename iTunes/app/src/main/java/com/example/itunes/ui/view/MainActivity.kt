package com.example.itunes.ui.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.itunes.R
import com.example.itunes.data.model.SingleTrack
import com.example.itunes.ui.adapter.CustomRecyclerViewAdapter
import com.example.itunes.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.InetAddress
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    private val tracksList = arrayListOf<SingleTrack>()
    private val mAdapter = CustomRecyclerViewAdapter(tracksList)
    //private var isInternetAvailable = false

    private val viewModelFactory by lazy{ ViewModelProvider.AndroidViewModelFactory.getInstance(application)}

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvArtistsGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = mAdapter
        }
        setUpSearchView()
        fetchAllTracksFromDB()
    }

    private fun fetchAllTracksFromDB() {
        viewModel.getTracksFromDB().observe(this,{
            if(!it.isNullOrEmpty()) {
                tracksList.clear()
                tracksList.addAll(it)
                mAdapter.notifyDataSetChanged()
            }
            else{
                Log.d("pulkit","null list all room")
            }
        })
    }

    private fun setUpSearchView() {
        svSearchArtists.isSubmitButtonEnabled = true
        svSearchArtists.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrBlank()) {
                    getTracksByQuery(query)
                    hideKeyboard(svSearchArtists)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        svSearchArtists.setOnCloseListener {
            hideKeyboard(svSearchArtists)
            return@setOnCloseListener true
        }
    }

    private fun getTracksByQuery(query: String) {
        if(isInternetAvailable()) {
            getTracksFromApi(query)
        }else{
            getTracksFromDB(query)
        }
    }

    private fun getTracksFromDB(query: String) {
        viewModel.getSearchedTracksFromDB(query).observe(this,{
            if(!it.isNullOrEmpty()) {
                tracksList.clear()
                tracksList.addAll(it)
                mAdapter.notifyDataSetChanged()
            }
            else{
                Log.d("pulkit","null list query room")
            }
        })
    }

    private fun getTracksFromApi(query: String) {
        val newQuery = query.replace(" ","+")
        viewModel.getAllTracks(newQuery).observe(this, Observer {
            if(!it.results.isNullOrEmpty()) {
                Log.d("pulkit",""+it.resultCount)
                tracksList.clear()
                tracksList.addAll(it.results)
                viewModel.insertFetchedTracksIntoDB(it.results)
                mAdapter.notifyDataSetChanged()
            }else
                Log.d("pulkit","null list!!!")

        })
    }

    private fun hideKeyboard(view: View) {
        (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken,0)
    }

    private fun isInternetAvailable():Boolean
    {
        try {
            var isAvail = false
            GlobalScope.launch(Dispatchers.IO) {
                val address = runBlocking { InetAddress.getByName("www.google.com") }
                isAvail = address.equals("")
            }
            return isAvail
        }catch (e:UnknownHostException){
            Log.d("internet","Exception while checking internet connectivity: ${e.message}")
        }
        return false
    }
}