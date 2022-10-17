package com.example.quiknews.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsWireApi {

    companion object{
        const val API_KEY="Qu5TE4pLAjscRqUAoHpNiGmM1qZkyHzq"
    }

    @GET("content/{source}/{section}.json")
    suspend fun getNewsWireApi(
        @Path("source") source:String,
        @Path("section") section:String,
        @Query("api-key") apiKey:String= API_KEY,
    ): NewsWireDto

}