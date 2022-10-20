package com.example.quiknews.presentation.utils


import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

fun getArticleDate(date:String):String{
    var articleDate=""
    for(i in 0..15) {
        if (date[i] == 'T') {
            articleDate += " "
        } else {
            articleDate += date[i]
        }
    }

return articleDate
}