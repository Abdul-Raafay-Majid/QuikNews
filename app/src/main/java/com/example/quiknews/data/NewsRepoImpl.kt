package com.example.quiknews.data

import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.data.local.NewsDao
import com.example.quiknews.data.remote.NewsWireApi
import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.repository.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsWireApi: NewsWireApi
) : NewsRepo {
    override suspend fun insertArticles(articles: List<ArticleEntity>) {

    }

    override suspend fun deleteArticles(articlesId: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllArticles() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllArticles(): Flow<List<ArticleEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getArticleById(id: Int): List<ArticleEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsWireApiData(
        source: String,
        section: String
    ): Flow<Resource<NewsWireDto>> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(Resource.Loading())
                val newsArticle = newsDao.getAllArticles()?: emptyList<List<ArticleEntity>>()
                lateinit var data:NewsWireDto
                try {
                     val data = newsWireApi.getNewsWireApi(source, section)
                } catch (e: HttpException) {
                    emit(
                        Resource.Error(
                            "Oops something went wrong!"
                        )
                    )
                } catch (e: IOException) {
                    emit(Resource.Error("Check your internet connection!"))
                }

                emit(Resource.Success(data))


            }
        }
    }
}