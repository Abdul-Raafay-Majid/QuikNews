package com.example.quiknews.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun insertArticles(articles:List<Article>)

    suspend  fun deleteArticles(articlesId:List<Int>)

    suspend fun deleteAllArticles()

    suspend fun getAllArticles(): Flow<List<Article>>

    suspend fun getArticleById(id:Int): Flow<Article>

    suspend fun getNewsWireApiData(source:String,section:String):Flow<Resource<NewsWireDto>>
}