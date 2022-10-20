package com.example.quiknews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 2)
abstract class NewsDb: RoomDatabase() {

    abstract val newsDao: NewsDao

    companion object {
        const val DB_NAME = "news_db"
    }
}