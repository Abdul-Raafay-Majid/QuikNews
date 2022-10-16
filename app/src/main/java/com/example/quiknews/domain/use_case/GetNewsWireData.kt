package com.example.quiknews.domain.use_case

import com.example.quiknews.core.utils.Resource
import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.NewsWireUseCase
import com.example.quiknews.domain.repository.NewsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsWireData (
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(source:String,section:String): Flow<Resource<NewsWireDto>> {
        return newsRepo.getNewsWireApiData(source,section)
    }
}