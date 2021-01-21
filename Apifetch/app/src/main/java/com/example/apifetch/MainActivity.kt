  package com.example.apifetch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.example.apifetch.Client
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

  const val BASE_URL = "https://cat-fact.herokuapp.com"
  const val BASE_URL_GITHUB = "https://api.github.com"
  const val BASE_URL_WEATHER = "https://api.weatherapi.com"

  class MainActivity : AppCompatActivity() {

      private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        getCurrentData()

//        getGithubData()

        getWeatherData()

//        GlobalScope.launch(Dispatchers.Main) {
//            val response = withContext(Dispatchers.IO){Client.api.getData()}
//            response.enqueue(object : Callback<UserList> {
//                override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
//                    if(response.isSuccessful)
//                        tvStart.text = "success"
//                }
//
//                override fun onFailure(call: Call<UserList>, t: Throwable) {
//                        tvStart.text = "failure"
//                }
//            })
//
//        }
    }

      private fun getCurrentData()
      {
          val apu = Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
              .create(CatApi::class.java)

          GlobalScope.launch(Dispatchers.Main) {
              val response= withContext(Dispatchers.IO) {
                  apu.getCatFact().execute()
              }
              if (response.isSuccessful) {
                  tvStart.text = response.body()!!.text
              }else {
                  tvStart.text = "cancelled"
              }
          }
      }

      private fun getGithubData()
      {
          val apu = Retrofit.Builder()
              .baseUrl(BASE_URL_GITHUB)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
              .create(GithubApi::class.java)

          GlobalScope.launch(Dispatchers.Main) {
              val response= withContext(Dispatchers.IO) { apu.getUsers().execute() }
              if(response.isSuccessful)
              {
                  tvStart.text = response.body()!![0].login
              }
              else
              {
                    tvStart.text = "request failed"
              }
          }
      }

      private fun getWeatherData()
      {
          val apu = Retrofit.Builder()
              .baseUrl(BASE_URL_WEATHER)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
              .create(WeatherApi::class.java)

          GlobalScope.launch(Dispatchers.Main) {
              val response= withContext(Dispatchers.IO) { apu.getData().execute() }
              if(response.isSuccessful)
              {
                  tvStart.text = response.body()!!.weather[0].description
              }
              else
              {
                  tvStart.text = "request failed"
              }
          }
      }
  }