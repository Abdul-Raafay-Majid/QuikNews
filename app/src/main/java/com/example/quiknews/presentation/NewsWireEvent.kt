package com.example.quiknews.presentation

sealed class NewsWireEvent {
    data class GetArticles(val page:Int):NewsWireEvent()
    object ClearAllArticles:NewsWireEvent()
    data class RefreshArticles(val source:String, val section:String):NewsWireEvent()
}