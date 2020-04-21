package com.creater.themoviedatabase2.repository

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.creater.themoviedatabase2.database.DatabaseClass
import com.creater.themoviedatabase2.database.DatabaseMain
import com.creater.themoviedatabase2.database.DatabasesRatedMovie
import com.creater.themoviedatabase2.database.ImgdataDao
import com.creater.themoviedatabase2.util.Api_Service
import com.creater.themoviedatabase2.util.container
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class Repository(var application: Application) {
    private val BASE_ADDRESS = "https://api.themoviedb.org/3/movie/"
    private val API_KEY = "bd2d96b5aa386796eb751412f883911b"
    private val Language = "en-US"
    private val UPCOMINGMOVIES = "upcoming"
    private val TOPRATEDMOVIES = "top_rated"
    var Rated_Movie: Retrofit? = null
    var UpcomingMovies: Retrofit? = null
    private var page: Int = 1;
    var imgDao: ImgdataDao? = null
    var TopMovie: ImgdataDao? = null
    var livedata: LiveData<List<DatabaseClass>>? = null
    var databaseMain: DatabaseMain? = null
    var databasesRatedMovie: DatabasesRatedMovie? = null
    var livedataofTopRated: LiveData<List<DatabaseClass>>? = null

    init {
        databasesRatedMovie = DatabasesRatedMovie(application)
        databaseMain = DatabaseMain(application)
        imgDao = databaseMain?.imagDao()
        livedata = imgDao?.getAllData()
        TopMovie = databasesRatedMovie?.imagDao()
        livedataofTopRated = TopMovie?.getAllData()

    }


    fun alldata(): LiveData<List<DatabaseClass>> {
        return livedata!!
    }

    fun apiData() {
        Log.i("vik", "DataFromApi")
        UpcomingMovies = Retrofit.Builder().baseUrl(BASE_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create()).build()
       var api = UpcomingMovies?.create(Api_Service::class.java)
        api?.fatchAlllist(UPCOMINGMOVIES, API_KEY, Language, page)?.enqueue(object :
            Callback<container> {
            override fun onFailure(call: Call<container>, t: Throwable) {

            }
            override fun onResponse(call: Call<container>, response: Response<container>) {
                if (response.isSuccessful && response.body() != null) {
                    for (i in response.body()!!.results!!) {
                        Glide.with(application).asBitmap()
                            .load("https://image.tmdb.org/t/p/w500${i.poster_path}")
                            .listener(object : RequestListener<Bitmap> {
                                override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    var stream = ByteArrayOutputStream()
                                    resource?.compress(Bitmap.CompressFormat.PNG, 50, stream)
                                    var data: ByteArray = stream.toByteArray()
                                    when(response.code() )
                                    {
                                        200 ->{
                                        Thread(Runnable {
                                            imgDao?.insert(DatabaseClass(i.id, i.title, data))
                                        }).start()
                                    }
                                    }
                                    return true
                                }

                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    return true
                                }
                            }).submit()

                    }

                }
            }
        }
        )
    }

    fun TopRatedDataBase(): LiveData<List<DatabaseClass>> {
        return livedataofTopRated!!
    }

    fun RatedMoviesApi() {
        Rated_Movie = Retrofit.Builder().baseUrl(BASE_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create()).build()
       var api = Rated_Movie?.create(Api_Service::class.java)
        api?.fatchAlllist(TOPRATEDMOVIES, API_KEY, Language, page)?.enqueue(object :
            Callback<container> {
            override fun onFailure(call: Call<container>, t: Throwable) {

            }

            override fun onResponse(call: Call<container>, response: Response<container>) {
                if (response.isSuccessful && response.body() != null) {
                    for (i in response.body()!!.results!!) {
                        Glide.with(application).asBitmap()
                            .load("https://image.tmdb.org/t/p/w500${i.poster_path}")
                            .listener(object : RequestListener<Bitmap> {
                                override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    var stream = ByteArrayOutputStream()
                                    resource?.compress(Bitmap.CompressFormat.PNG, 50, stream)
                                   when(response.code())
                                   {
                                       200->{
                                           var data: ByteArray = stream.toByteArray()
                                           Thread(Runnable {
                                               TopMovie?.insert(DatabaseClass(i.id, i.title, data))
                                           }).start()
                                       }
                                   }
                                    return true
                                }

                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    return true
                                }
                            }).submit()
                    }
                }

            }
        }
        )
    }


}