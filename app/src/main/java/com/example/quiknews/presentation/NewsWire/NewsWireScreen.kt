package com.example.quiknews.presentation.NewsWire

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quiknews.presentation.NewsWireEvent
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.presentation.utils.Sections
import com.example.quiknews.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.ads.AdView
import java.util.jar.Attributes

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsWireScreen(
    modifier: Modifier = Modifier,
    newsWireViewModel: NewsWireViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val uiState by newsWireViewModel.newsWireState
    val isRefreshing = rememberSwipeRefreshState(isRefreshing = false)
    val context= LocalContext.current
    Box(
        modifier = modifier
    ) {
        SwipeRefresh(
            modifier = Modifier,
            state = isRefreshing,
            onRefresh = {
                newsWireViewModel.getNewsWireUseCases(
                    NewsWireEvent.RefreshArticles(pagerState.currentPage)
                )
            },
            refreshTriggerDistance = 100.dp,
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = Orange
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                SectionTabs(
                    modifier = Modifier.weight(1f),
                    sections = Sections.sections,
                    pagerState = pagerState,

                    )
                SectionContentScreen(
                    modifier = Modifier.weight(17f),
                    sections = Sections.sections,
                    pagerState = pagerState,
                    newsWireState = uiState,
                    newsWireViewModel = newsWireViewModel

                )

            }
        }
    }
}









