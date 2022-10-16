package com.example.quiknews.di

import android.app.Application
import androidx.room.Room
import com.example.quiknews.data.NewsRepoImpl
import com.example.quiknews.data.local.NewsDao
import com.example.quiknews.data.local.NewsDb
import com.example.quiknews.data.remote.NewsWireApi
import com.example.quiknews.domain.NewsWireUseCase
import com.example.quiknews.domain.repository.NewsRepo
import com.example.quiknews.domain.use_case.GetNewsWireData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsWireModule {

    @Provides
    @Singleton
    fun providesNewsWireApi():NewsWireApi{
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NewsWireApi::class.java)
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
        return NewsRepoImpl(db.newsDao,api)
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
            getNewsWireData = GetNewsWireData(newsRepo = repo)
        )
    }
}