package com.creater.themoviedatabase2.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.creater.themoviedatabase2.database.DatabaseClass
import com.creater.themoviedatabase2.repository.Repository

/** Main Class of the App which will be visibale to the user **/
class MainModelClass(application: Application) : AndroidViewModel(application) {
    var repository: Repository = Repository(application)
    fun getRoomData(): LiveData<List<DatabaseClass>>   //GetTheLiveDataFromDataBase
    {
        return repository.alldata()
    }

    fun getApiData()   //GetDataFromUpcomingMovies
    {
        repository.apiData()
    }

    fun getRoomdataTopMovies(): LiveData<List<DatabaseClass>> {
        return repository.TopRatedDataBase()
    }

    fun getDataApi() {
        return repository.RatedMoviesApi()
    }

}