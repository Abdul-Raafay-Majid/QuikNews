package com.example.quiknews.data.local

 abstract class NewsDb {

     abstract val newsDao:NewsDao

     companion object{
         const val DB_NAME="news_db"
     }
}