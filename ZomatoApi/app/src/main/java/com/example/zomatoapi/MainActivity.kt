package com.example.zomatoapi

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),LocationListener {

    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!checkPermission()) {
            askPermission()
        }else
        {
            tvStart.text = "fetching location..."
//            tvStart.visibility=View.GONE
//            rv_restaurants.visibility=View.VISIBLE
            getLocation()

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

    @SuppressLint("MissingPermission")
    private fun getLocation()
    {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,50000000,5000f,this)
    }

    override fun onLocationChanged(location: Location) {
        tvStart.text = "lat: ${location.latitude} \n long: ${location.longitude}"
    }

}

