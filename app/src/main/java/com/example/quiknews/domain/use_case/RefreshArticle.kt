package com.example.quiknews.domain.use_case

import com.example.quiknews.core.utils.Resource
import com.example.quiknews.domain.model.NewsWireInfo
import com.example.quiknews.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow

class RefreshArticle(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(source:String,section:String): Flow<Resource<NewsWireInfo>> {
       return newsRepo.refreshArticle(source, section)
    }
}