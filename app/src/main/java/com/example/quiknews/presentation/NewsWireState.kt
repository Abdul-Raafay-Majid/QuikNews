package com.example.quiknews.presentation

import com.example.quiknews.data.remote.NewsWireDto

data class NewsWireState(
    val newsWireDto: NewsWireDto?=null,
    val isLoading:Boolean=false,
    val error:String?=null

)