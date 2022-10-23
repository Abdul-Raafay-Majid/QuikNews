package com.example.quiknews.data

import android.util.Log
import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.data.local.NewsDao
import com.example.quiknews.data.remote.NewsWireApi
import com.example.quiknews.domain.model.NewsWireInfo
import com.example.quiknews.domain.repository.NewsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsWireApi: NewsWireApi,
    private val newsDao: NewsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsRepo {

    override suspend fun deleteAllArticles() {
        withContext(ioDispatcher) {
            newsDao.deleteAllArticles()
        }
    }

    override suspend fun getNewsWireApiData(
        source: String,
        section: String
    ): Flow<Resource<NewsWireInfo>> {
        return withContext(ioDispatcher) {
            flow {
                emit(Resource.Loading(message = "Loading"))
                var dbData: List<ArticleEntity>? = newsDao.getArticleBySection(section)
                if (dbData.isNullOrEmpty()) {
                    try {
                        val remoteData =
                            newsWireApi.getNewsWireApi(source = source, section = section)
                                .getNewsWireInfo(section)
                        Log.d("API", "API CALLED")
                        newsDao.insertArticles(remoteData.articles)
                    } catch (e: HttpException) {
                        emit(
                            Resource.Error(
                                "Oops something went wrong! "
                            )
                        )
                    } catch (e: IOException) {
                        emit(Resource.Error("Check your internet connection! "))
                    }
                }

                dbData = newsDao.getArticleBySection(section)
                val newsData = NewsWireInfo(
                    articles = dbData,
                    section = section
                )
                emit(Resource.Success(newsData))
            }
        }


    }

    override suspend fun refreshArticle(
        source: String,
        section: String
    ): Flow<Resource<NewsWireInfo>> {
        return withContext(ioDispatcher) {
            flow {
                emit(Resource.Loading(message = "Loading"))
                try {
                    newsDao.deleteArticles(section)
                    val remoteData =
                        newsWireApi.getNewsWireApi(source = source, section = section)
                            .getNewsWireInfo(section)
                    Log.d("API", "API CALLED")
                    newsDao.insertArticles(remoteData.articles)
                } catch (e: HttpException) {
                    emit(
                        Resource.Error(
                            "Oops something went wrong!"
                        )
                    )
                } catch (e: IOException) {
                    emit(Resource.Error("Check your internet connection!"))
                }
                val dbData = newsDao.getArticleBySection(section)
                val newsData = NewsWireInfo(
                    articles = dbData,
                    section = section
                )
                emit(Resource.Success(newsData))
            }
        }

    }
}