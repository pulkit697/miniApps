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
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zomatoapi.data.GeoCode
import com.example.zomatoapi.data.SearchResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory

const val BASE_URL = "https://developers.zomato.com/api/v2.1/"

class MainActivity : AppCompatActivity(),LocationListener {

    var adapter = CustomAdapter(listOf())
    var adapter2 = CustomAdapterSearch(listOf())

    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            tvStart.visibility=View.VISIBLE
            rv_restaurants.visibility=View.GONE
            rv_search.visibility = View.GONE


        bt_search.setOnClickListener {
            if(etSearch.text.isNotEmpty())
            {
                search(etSearch.text.toString())
            }
        }


        if(!checkPermission()) {
            askPermission()
            if(checkPermission())
                tvStart.text = "Please restart app."
        }
        if(checkPermission())
        {
//            tvStart.visibility=View.GONE
//            rv_restaurants.visibility=View.VISIBLE
            getLocation()
        }
        rv_restaurants.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        rv_search.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter2
        }
//        fetchRestaurantList(29.469233,77.716528)
    }



    private fun checkPermission():Boolean {
        return ActivityCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    private fun askPermission() {
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
                getLocation()
            }else{
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        tvStart.text = "fetching location..."
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500000,5000f,this)
        Log.d("flags","checking location.....")
    }

    override fun onLocationChanged(location: Location) {
        tvStart.text = "lat: ${location.latitude} \n long: ${location.longitude}"
        Log.d("flags","lat: ${location.latitude}")
        tvStart.text = "location fetched,\n fetching restaurants..."
        fetchRestaurantList(location.latitude,location.longitude)
    }

    private fun fetchRestaurantList(lat:Double,long: Double) {
        adapter.list= listOf()
        val apu = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ZomatoApi::class.java)
        GlobalScope.launch(Dispatchers.Main){
            val response = withContext(Dispatchers.IO){apu.getRestaurantsByLocation(lat,long).execute()}
            if(response.isSuccessful){
                val size = response.body()!!.nearby_restaurants.size
                tvStart.text = size.toString()
                fillList(response)
            }else{
                tvStart.text = "failure"
            }
        }
    }

    private fun fillList(response: Response<GeoCode>?) {
        tvStart.visibility=View.GONE
        rv_restaurants.visibility = View.VISIBLE
        rv_search.visibility = View.GONE
        adapter.list = response!!.body()!!.nearby_restaurants
    }

    private fun fillList_search(response: Response<SearchResult>?) {
        tvStart.visibility=View.GONE
        rv_restaurants.visibility = View.GONE
        rv_search.visibility = View.VISIBLE
        adapter2.list = response!!.body()!!.restaurants
    }

    private fun search(query:String)
    {
        adapter2.list = listOf()
        val apu = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ZomatoApi::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val response= withContext(Dispatchers.IO) { apu.getRestaurantsBySearch(query).execute() }
            if(response.isSuccessful) {
                val size = response.body()!!.restaurants.size
                tvStart.text = size.toString()
                fillList_search(response)
            } else {
                tvStart.text = "request failed"
            }
        }

    }

}

