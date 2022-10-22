package com.example.quiknews.di

import android.app.Application
import androidx.room.Room
import com.example.quiknews.data.NewsRepoImpl
import com.example.quiknews.data.local.NewsDao
import com.example.quiknews.data.local.NewsDb
import com.example.quiknews.data.remote.NewsWireApi
import com.example.quiknews.domain.NewsWireUseCase
import com.example.quiknews.domain.repository.NewsRepo
import com.example.quiknews.domain.use_case.DeleteAllArticles
import com.example.quiknews.domain.use_case.GetNewsWireData
import com.example.quiknews.domain.use_case.RefreshArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsWireModule {

    @Provides
    @Singleton
    fun providesNewsWireApi():NewsWireApi{
        return Retrofit.Builder().baseUrl(" https://api.nytimes.com/svc/news/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideNewsDb(app:Application): NewsDb{
        return Room.databaseBuilder(
            app,
            NewsDb::class.java,
            NewsDb.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNewRepo(api:NewsWireApi,db:NewsDb):NewsRepo{
        return NewsRepoImpl(newsWireApi = api,db.newsDao)
    }

    @Provides
    @Singleton
    fun providesNewsDao(db:NewsDb):NewsDao{
        return db.newsDao
    }

    @Provides
    @Singleton
    fun providesNewsWireUseCase(repo: NewsRepo):NewsWireUseCase{
        return NewsWireUseCase(
            getNewsWireData = GetNewsWireData(newsRepo = repo),
            deleteAllArticles = DeleteAllArticles(newsRepo = repo),
            refreshArticle = RefreshArticle(newsRepo = repo)
        )
    }

    @Provides
    @Singleton
    fun provideIODispatcher():CoroutineDispatcher{
        return Dispatchers.IO
    }
}