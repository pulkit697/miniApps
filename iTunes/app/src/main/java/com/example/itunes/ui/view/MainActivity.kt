package com.example.itunes.ui.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import java.io.IOException
import java.lang.Exception
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    private val tracksList = arrayListOf<SingleTrack>()
    private val mAdapter = CustomRecyclerViewAdapter(tracksList)
    private var isInternetAvailable = false

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
        attachBroadcastReceiver()
        fetchAllTracksFromDB()
    }

    private fun attachBroadcastReceiver() {
        val intentFilter = IntentFilter().apply {
            addAction("android.net.conn.CONNECTIVITY_CHANGE")
            addAction("android.net.wifi.WIFI_STATE_CHANGED")
        }
        val receiver  = NetworkChangeReceiver()
        registerReceiver(receiver,intentFilter)
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
                tvRecentBox.visibility = View.GONE
                return true
            }
        })
        svSearchArtists.setOnCloseListener {
            hideKeyboard(svSearchArtists)
            fetchAllTracksFromDB()
            tvRecentBox.visibility = View.VISIBLE
            return@setOnCloseListener true
        }
    }

    private fun getTracksByQuery(query: String) {
        if(isInternetAvailable) {
            getTracksFromApi(query)
        }else{
            getTracksFromDB(query)
        }
    }

    private fun getTracksFromDB(query: String) {
        viewModel.getSearchedTracksFromDB(query).observe(this,{
            tracksList.clear()
            if(!it.isNullOrEmpty()) {
                tracksList.addAll(it)
            }
            else{
                Log.d("pulkit","null list query room")
            }
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun getTracksFromApi(query: String) {
        val newQuery = query.replace(" ","+")
        viewModel.getAllTracks(newQuery).observe(this, Observer {
            tracksList.clear()
            if(!it.results.isNullOrEmpty()) {
                Log.d("pulkit",""+it.resultCount)
                tracksList.addAll(it.results)
                viewModel.insertFetchedTracksIntoDB(it.results)
            }else
                Log.d("pulkit","null list!!!")
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun hideKeyboard(view: View) {
        (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken,0)
    }

    fun checkingInternet() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val sock = Socket()
                val something = GlobalScope.async(Dispatchers.IO) { sock.connect(InetSocketAddress("8.8.8.8", 53), 1500) }
                something.await()
                GlobalScope.launch(Dispatchers.IO) { sock.close() }
                isInternetAvailable = true
                withContext(Dispatchers.Main) {hideUnhideInternetWarning()}
            } catch (e: IOException) {
                Log.d("pulkit","internet not connected ${e.message}")
                isInternetAvailable = false
                withContext(Dispatchers.Main) {hideUnhideInternetWarning()}
            }
        }
    }

    private fun hideUnhideInternetWarning() {
        if(isInternetAvailable)
            tvNoInternetWarning.visibility = View.GONE
        else
            tvNoInternetWarning.visibility = View.VISIBLE
    }

    inner class NetworkChangeReceiver:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            checkingInternet()
//            hideUnhideInternetWarning()
        }
    }
}