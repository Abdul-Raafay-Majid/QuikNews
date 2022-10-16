package com.example.quiknews.data

import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.local.NewsDao
import com.example.quiknews.data.remote.NewsWireApi
import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.model.Article
import com.example.quiknews.domain.repository.NewsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NewsRepoImpl(
    private val newsDao: NewsDao,
    private val newsWireApi: NewsWireApi
) : NewsRepo {
    override suspend fun insertArticles(articles: List<Article>) {

    }

    override suspend fun deleteArticles(articlesId: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllArticles() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun getArticleById(id: Int): Flow<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsWireApiData(source:String, section:String): Flow<Resource<NewsWireDto>> {
        return withContext(Dispatchers.IO) {
            flow {
                val newsArticle= newsDao.getAllArticles()
                emit(Resource.Loading(message = "Loading News...."))
                try {
                     val data = newsWireApi.getNewsWireApi(source, section)
                    emit(Resource.Success(data,message = "Loaded successfully"))
                } catch(e:HttpException){
                    emit(Resource.Error(
                        "Oops something went wrong!"
                    ))
                } catch(e:IOException){
                    emit(Resource.Error("Check your internet connection!"))
                }


            }
        }
    }
}