package com.example.quiknews.domain

import com.example.quiknews.data.remote.NewsWireDto
import com.example.quiknews.domain.use_case.DeleteAllArticles
import com.example.quiknews.domain.use_case.GetNewsWireData
import javax.inject.Inject

data class NewsWireUseCase (
    val getNewsWireData: GetNewsWireData,
    val deleteAllArticles: DeleteAllArticles
)