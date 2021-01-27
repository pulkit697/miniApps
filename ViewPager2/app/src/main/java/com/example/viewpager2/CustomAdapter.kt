package com.example.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomAdapter(fa:FragmentActivity):FragmentStateAdapter(fa) {

    private val list = arrayListOf<Fragment>()

    fun add(fragment: Fragment) { list.add(fragment) }

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]

}