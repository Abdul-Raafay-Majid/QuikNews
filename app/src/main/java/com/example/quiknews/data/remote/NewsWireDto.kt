package com.example.quiknews.data.remote

import com.example.quiknews.domain.model.ArticleDto

data class NewsWireDto(
    val status:String,
    val copyright:String,
    val num_results:Int,
    val results:List<ArticleDto>
) {

     fun toListOfNewsData(): List<ArticleDto> {
        return results
    }
}
