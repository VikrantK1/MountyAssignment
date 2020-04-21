package com.creater.themoviedatabase2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.creater.themoviedatabase2.database.DatabaseClass
import com.creater.themoviedatabase2.repository.Repository

class MainModelClass(application: Application) : AndroidViewModel(application) {
lateinit var repository: Repository
    init {
        repository= Repository(application)
    }
    fun getRoomData():LiveData<List<DatabaseClass>>
    {
        return repository.alldata()
    }
    fun getApiData()
    {
        repository.apiData()
    }
    fun getRoodataTopMovies():LiveData<List<DatabaseClass>>
    {
        return repository.TopRatedDataBase()
    }
    fun getDataApi()
    {
        return repository.RatedMoviesApi()
    }

}