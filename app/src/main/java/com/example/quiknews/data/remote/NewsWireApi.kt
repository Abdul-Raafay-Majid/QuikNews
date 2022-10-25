package com.example.quiknews.data.remote

import com.example.quiknews.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsWireApi {

    @GET("content/{source}/{section}.json")
    suspend fun getNewsWireApi(
        @Path("source") source:String,
        @Path("section") section:String,
        @Query("api-key") apiKey:String= BuildConfig.API_KEY,
    ): NewsWireDto

}