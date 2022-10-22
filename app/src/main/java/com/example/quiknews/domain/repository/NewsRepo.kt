package com.example.quiknews.domain.repository

import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.domain.model.NewsWireInfo
import kotlinx.coroutines.flow.Flow

interface NewsRepo {


    suspend fun deleteAllArticles()

    suspend fun getNewsWireApiData(source:String,section:String):Flow<Resource<NewsWireInfo>>

    suspend fun refreshArticle(source:String,section: String):Flow<Resource<NewsWireInfo>>
}