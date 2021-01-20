package com.example.zomatoapi

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!checkPermission()) {
            askPermission()
        }else
        {
//            tvStart.visibility=View.GONE
//            rv_restaurants.visibility=View.VISIBLE

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        return true
    }

    private fun checkPermission():Boolean
    {
        return ActivityCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission()
    {
        val permissionsList = arrayOf(ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this,permissionsList,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1)
        {
            if(checkPermission())
            {
                Toast.makeText(this,"permission allowed",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

