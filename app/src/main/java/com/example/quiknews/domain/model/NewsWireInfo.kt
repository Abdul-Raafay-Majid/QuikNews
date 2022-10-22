package com.example.quiknews.domain.model

import com.example.quiknews.data.local.ArticleEntity

data class NewsWireInfo(
    val articles:List<ArticleEntity> = emptyList(),
    val section:String
)
