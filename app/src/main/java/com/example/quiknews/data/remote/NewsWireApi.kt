package com.example.quiknews.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NewsWireApi {

    companion object{
        const val API_KEY="Qu5TE4pLAjscRqUAoHpNiGmM1qZkyHzq"
    }

    @GET("svc/news/v3/content/section-list.json?api-key=$API_KEY")
    fun getNewsWireApi(
     @Path("source") source:String,
     @Path("section") section:String
    ): NewsWireDto

}