package com.example.mvvmgithubapi.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmgithubapi.R
import com.example.mvvmgithubapi.data.model.GithubUser
import com.example.mvvmgithubapi.ui.adapters.UsersListAdapter
import com.example.mvvmgithubapi.ui.viewmodels.UsersListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(UsersListViewModel::class.java)
    }

    val usersList = arrayListOf<GithubUser>()
    val adapter = UsersListAdapter(usersList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvUsersList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        initAllUsers()
        setUpSearchView()
    }

    private fun initAllUsers() {
        viewModel.fetchUsers()
        viewModel.users.observe(this, {
            if(!it.isNullOrEmpty())
            {
                usersList.clear()
                usersList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun setUpSearchView() {
        svSearchUsers.isSubmitButtonEnabled = true
        svSearchUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query.isNullOrEmpty())
                    Log.d("pulkit","query empty")
                query?.let {
//                    Log.d("pulkit",query)
                    findUsers(query)
                }
            return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        svSearchUsers.setOnCloseListener {
            usersList.clear()
            viewModel.users.value?.let { usersList.addAll(it) }
            return@setOnCloseListener true
        }
    }

    private fun findUsers(name: String) {
        viewModel.fetchSearchedUsers(name).observe(this, {
        Log.d("pulkit","searched $name")
            if (!it.isNullOrEmpty()) {
            Log.d("pulkit",""+it.size)
                usersList.clear()
                usersList.addAll(it)
                adapter.notifyDataSetChanged()
            }
            else
                Log.d("pulkit","null list")
        })
    }
}