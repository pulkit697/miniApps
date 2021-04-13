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
import androidx.room.Room
import com.example.itunes.R
import com.example.itunes.data.db.TracksDatabase
import com.example.itunes.data.model.SingleTrack
import com.example.itunes.ui.adapter.CustomRecyclerViewAdapter
import com.example.itunes.ui.viewmodel.MainActivityViewModel
import com.example.itunes.utils.DB_NAME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tracksList = arrayListOf<SingleTrack>()
    private val mAdapter = CustomRecyclerViewAdapter(tracksList)

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvArtistsGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = mAdapter
        }
        setUpSearchView()
    }

    private fun setUpSearchView() {
        svSearchArtists.isSubmitButtonEnabled = true
        svSearchArtists.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrBlank()) {
                    getTracks(query)
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

    private fun getTracks(query: String) {
        val newQuery = query.replace(" ","+")
        viewModel.getAllTracks(newQuery).observe(this, Observer {
            if(!it.results.isNullOrEmpty()) {
                Log.d("pulkit",""+it.resultCount)
                tracksList.clear()
                tracksList.addAll(it.results)
                mAdapter.notifyDataSetChanged()
            }else
                Log.d("pulkit","null list!!!")

        })
    }

    private fun hideKeyboard(view: View) {
        (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken,0)
    }
}