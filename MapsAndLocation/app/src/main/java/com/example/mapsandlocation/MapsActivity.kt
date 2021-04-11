package com.example.mapsandlocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_maps)
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap
            mMap.uiSettings.apply {
                isCompassEnabled = true
                isZoomControlsEnabled = true
                isZoomGesturesEnabled = true
                isScrollGesturesEnabled = true
                isTiltGesturesEnabled = true
            }
            locationSeeker()
        }

        private fun locationSeeker()
        {
            if(isFineLocationAllowed())
            {
                checkingAndAskingLocation()
            }else
            {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),999)
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if(requestCode==999)
            {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    checkingAndAskingLocation()
                else
                    Toast.makeText(this,"Location permission not permitted",Toast.LENGTH_SHORT).show()
            }
        }

        private fun checkingAndAskingLocation() {
            if(isGPSEnabled())
                setUpLocationListener()
            else
                AlertDialog.Builder(this)
                    .setTitle("Enable GPS")
                    .setMessage("GPS needs to be turned on in order to fetch location")
                    .setCancelable(false)
                    .setPositiveButton("Enable now") { dialogInterface: DialogInterface, _ ->
                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        dialogInterface.dismiss()
                    }
                        .show()
        }

        @SuppressLint("MissingPermission")
        private fun setUpLocationListener() {

            val providers = locationManager.getProviders(true)
            var loc:Location?= null
            for(i in providers.indices.reversed())
            {
                loc = locationManager.getLastKnownLocation(providers[i])
                if(loc!=null)   break
            }
            loc?.let {
                addMarker(loc.latitude,loc.longitude)
            }
        }

        private fun addMarker(lat:Double,lng:Double) {
            val current_location = LatLng(lat, lng)
            mMap.addMarker(MarkerOptions().position(current_location).title("Marker in Sydney"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current_location,15f))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//        mMap.addPolyline(
//            PolylineOptions()
//                .add(sydney,LatLng(25.59,25.59))
//                .color(ContextCompat.getColor(baseContext,R.color.design_default_color_primary))
//        )
//            .width = 2f
        }

        private fun isGPSEnabled():Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        private fun isFineLocationAllowed(): Boolean = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
}