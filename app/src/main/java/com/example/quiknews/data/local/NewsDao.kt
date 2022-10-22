package com.example.quiknews.data.local

import androidx.room.*

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles:List<ArticleEntity>)

    @Query("DELETE FROM articleentity WHERE id IN (:articlesId)")
   suspend  fun deleteArticles(articlesId:List<Int>)

   @Query("DELETE FROM ArticleEntity")
   suspend fun deleteAllArticles()

   @Query("SELECT * FROM articleentity")
   suspend fun getAllArticles(): List<ArticleEntity>?

   @Query("SELECT * FROM articleentity WHERE section=:section")
   suspend fun getArticleBySection(section:String):List<ArticleEntity>
}