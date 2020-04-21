package com.creater.themoviedatabase2.util

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/** Retrofit intreface to download data **/
interface Api_Service {
    @GET("{type}")
    fun fatchAlllist(
        @Path("type") type: String, @Query("api_key") key: String, @Query("language") lan: String, @Query(
            "page"
        )
        page: Int
    ): Call<container>

}