package com.example.quiknews.data.remote

import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.domain.model.NewsWireInfo

data class NewsWireDto(
    val status:String,
    val copyright:String,
    val num_results:Int,
    val results:List<ArticleDto>
) {
     fun getNewsWireInfo(section:String):NewsWireInfo{
         return NewsWireInfo(
             articles = results.map { articleDto->
                 ArticleEntity(
                     section=section,
                     subsection =articleDto.subsection,
                     title=articleDto.title,
                     abstraction = articleDto.abstract,
                     url=articleDto.url,
                     uri=articleDto.uri,
                     byline = articleDto.byline,
                     thumbnail_standard = articleDto.thumbnail_standard,
                     item_type = articleDto.item_type,
                     source = articleDto.source,
                     updated_date = articleDto.updated_date,
                     material_type_facet = articleDto.material_type_facet,
                     kicker = articleDto.kicker,
                     subheadline = articleDto.subheadline
                 )
             },
             section = section
         )

    }
}
