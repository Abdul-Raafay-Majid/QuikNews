package com.example.quiknews.domain.repository

import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun insertArticles(articles:List<ArticleEntity>)

    suspend  fun deleteArticles(articlesId:List<Int>)

    suspend fun deleteAllArticles()

    suspend fun getAllArticles(): Flow<List<ArticleEntity>>

    suspend fun getArticleById(id:Int): List<ArticleEntity>

    suspend fun getNewsWireApiData(source:String,section:String):Flow<Resource<List<ArticleEntity>>>
}