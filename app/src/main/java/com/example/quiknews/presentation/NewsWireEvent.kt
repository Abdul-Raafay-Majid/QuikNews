package com.example.quiknews.presentation

sealed class NewsWireEvent {
    data class GetArticles(val source:String, val section:String):NewsWireEvent()
}