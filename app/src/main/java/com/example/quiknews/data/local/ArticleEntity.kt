package com.example.quiknews.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    val section: String,
    val subsection: String,
    val title: String,
    val uri: String,
    val url: String,
    val byline: String,
    val thumbnail_standard: String,
    val item_type: String,
    val source: String,
    val updated_date: String,
    val material_type_facet: String,
    val kicker: String,
    val subheadline: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)
