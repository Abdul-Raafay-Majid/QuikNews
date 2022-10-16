package com.example.quiknews.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey val  id:Int=0,
    val section:String,
    val subsection:String,
    val title:String,
    val abstract:String,
    val uri:String,
    val url:String,
    val byline:String,
    val thumbnail_standard:String,
    val item_type:String,
    val source:String,
    val updated_date:String,
    val material_type_facet:String,
    val kicker:String,
    val subheadline:String
)
