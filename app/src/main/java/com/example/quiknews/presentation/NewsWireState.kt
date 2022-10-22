package com.example.quiknews.presentation

import androidx.compose.ui.res.stringResource
import com.example.quiknews.R
import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.data.remote.NewsWireDto

data class NewsWireState(
    val newsWireArticles: List<ArticleEntity>? =null,
    val isLoading:Boolean=false,
    val error:String?=null,
    val section:String?="all"

)