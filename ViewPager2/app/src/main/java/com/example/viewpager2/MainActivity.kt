package com.example.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.transition.Transition
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CustomAdapter(this)
        adapter.apply {
            add(BlankFragment())
            add(BlankFragment2())
            add(BlankFragment3())
        }

        vpLayout.apply {
            this.adapter = adapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//           Auto scroll little bit based on value passed
//            beginFakeDrag()
//            fakeDragBy(-1f)
//            endFakeDrag()
            setPageTransformer(DepthPageTransformer())
        }

            TabLayoutMediator(tlTab,vpLayout) { tab,position->
                tab.text = "Tab $position"
            }.attach()
    }

}