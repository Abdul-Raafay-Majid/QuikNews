package com.example.quiknews.data.local

import androidx.room.*
import com.example.quiknews.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles:List<Article>)

    @Query("DELETE FROM article WHERE id IN (:articlesId)")
   suspend  fun deleteArticles(articlesId:List<Int>)

   @Query("DELETE FROM Article")
   suspend fun deleteAllArticles()

   @Query("SELECT * FROM article")
   suspend fun getAllArticles(): Flow<List<Article>>?

   @Query("SELECT * FROM article WHERE id=:id LIMIT 1 ")
   suspend fun getArticleById(id:Int):Flow<Article>?
}