package com.creater.themoviedatabase2

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.creater.themoviedatabase2.ViewModel.MainModelClass
import com.creater.themoviedatabase2.database.DatabaseClass
import com.creater.themoviedatabase2.database.DatabaseMain
import com.creater.themoviedatabase2.database.DatabasesRatedMovie
import com.creater.themoviedatabase2.util.Api_Service
import com.creater.themoviedatabase2.util.TopRatedMovies
import com.creater.themoviedatabase2.util.UpcomingMovies
import com.creater.themoviedatabase2.util.container
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import retrofit2.Callback as Callback

class MainActivity : AppCompatActivity() {
    var adapterTopRatedMovies: TopRatedMovies? = null
    var adapter12: UpcomingMovies? = null
    lateinit var mainModelClass: MainModelClass
    //widget
    lateinit var UpcomingMovies_Recycler: RecyclerView
    lateinit var TopRatedRecyclerView: RecyclerView
    //widget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initilation
        UpcomingMovies_Recycler = findViewById(R.id.UpcomingMovieRecycler)
        TopRatedRecyclerView = findViewById(R.id.TopRateMovie_Recycler)
        //initilation
        TopRatedRecyclerView?.layoutManager = GridLayoutManager(this, 3) //TopMovieRecyclerview
        UpcomingMovies_Recycler?.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
        mainModelClass =
            ViewModelProvider(this).get(MainModelClass::class.java)  //ViewModelProvider
        if (isNetworkConnected()) {
            mainModelClass.getApiData()  //UpComing Movie
            mainModelClass.getDataApi()  //TopRated Movie
        } else {
            Toast.makeText(applicationContext, "network is Not Connected", Toast.LENGTH_LONG).show()
        }
        mainModelClass.getRoomData().observe(this,
            Observer<List<DatabaseClass>> { t ->
                UpcomingMovies_Recycler.adapter = UpcomingMovies(this@MainActivity, t!!)
            }
        )
        mainModelClass.getRoomdataTopMovies().observe(this, Observer<List<DatabaseClass>> { s ->
            TopRatedRecyclerView.adapter =
                TopRatedMovies(this@MainActivity, s) //adapter to TopMovies RecyclerView
        })

    }

    fun isNetworkConnected(): Boolean {
        var contectivity: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return contectivity.isActiveNetworkMetered
    }


}

