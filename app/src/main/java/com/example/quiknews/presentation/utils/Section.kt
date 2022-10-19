package com.example.quiknews.presentation.utils

import android.media.tv.TvContract.Programs.Genres.*
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable

typealias SectionScreen=@Composable ()->Unit

sealed class Sections(
    val display_name:String,
    val pathParam:String,
    val screen:SectionScreen
){

    companion object{
        val sections= listOf<Section>(
            Section.ALL,
            Section.BRIEFING,
            Section.BUSINESS,
            Section.TECHNOLOGY,
            Section.SCIENCE,
            Section.SPORTS,
            Section.AUTOMOBILES,
            Section.TRAVEL,
            Section.WORLD,
            Section.EDUCATION,
            Section.FASHION,
            Section.FOOD,
            Section.HEAlTH,
            Section.MOVIES,
            Section.CLIMATE,
            Section.VIDEO,
            Section.OBITUARIES,
            Section.PODCASTS,
            Section.REAL_ESTATE,
        )
    }
}
enum class Section(val display_name:String, val pathParam:String) {
    ALL("All","all"),
    ADMIN("Admin","admin"),
    ARTS("Arts","arts"),
    AUTOMOBILES("Automobiles","automobiles"),
    BOOKS("Books","books"),
    BRIEFING("Briefing","briefing"),
    BUSINESS("Business","business"),
    CLIMATE("Climate","climate"),
    CORRECTIONS("Corrections","corrections"),
    CROSSWORDS_AND_GAMES("Crosswords & Games","crosswords & games"),
    EDUCATION("Education","education"),
    FASHION("Fashion","fashion"),
    FOOD("Food","food"),
    GUIDES("Guides","guides"),
    HEAlTH("Health","health"),
    HOME_AND_GARDEN("Home & Garden","home & garden"),
    HOME_PAGE("Home Page","home page"),
    JOB_MARKET("Job Market","job market"),
    LENS("Lens","lens"),
    MAGAZINE("Magazine","magazine"),
    MOVIES("Movies","movies"),
    MULTI_MEDIA_PHOTOS("Multimedia/Photos","multimedia/photos"),
    OBITUARIES("Obituaries","obituaries"),
    NEW_YORK("New York","new york"),
    OPINION("Opinion","opinion"),
    PARENTING("Parenting","parenting"),
    PODCASTS("Podcasts","podcasts"),
    REAL_ESTATE("Real Estate","real estate"),
    SCIENCE("Science","science"),
    SPORTS("Sports","sports"),
    STYLE("Style","style"),
    TECHNOLOGY("Technology","technology"),
    TODAYS_PAPER("Today's Paper","today's paper"),
    TRAVEL("Travel","travel"),
    VIDEO("Video","video"),
    WORLD("World","world"),
}