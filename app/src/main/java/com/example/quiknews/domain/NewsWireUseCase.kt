package com.example.quiknews.domain

import com.example.quiknews.domain.use_case.DeleteAllArticles
import com.example.quiknews.domain.use_case.GetNewsWireData
import com.example.quiknews.domain.use_case.RefreshArticle

data class NewsWireUseCase (
    val getNewsWireData: GetNewsWireData,
    val deleteAllArticles: DeleteAllArticles,
    val refreshArticle: RefreshArticle,
)