package com.example.quiknews.presentation

import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.data.remote.NewsWireDto

data class NewsWireState(
    val newsWireArticles: List<ArticleEntity>? =null,
    val isLoading:Boolean=false,
    val error:String?=null

)