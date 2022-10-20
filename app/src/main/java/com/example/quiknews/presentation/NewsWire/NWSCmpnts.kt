package com.example.quiknews.presentation.NewsWire

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.quiknews.data.local.ArticleEntity
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.presentation.NewsWireEvent
import com.example.quiknews.presentation.NewsWireState
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.presentation.utils.Section
import com.example.quiknews.presentation.utils.getArticleDate
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SectionTabs(
    modifier:Modifier=Modifier,
    sections:List<Section>,
    pagerState: PagerState,
    viewModel: NewsWireViewModel
){
    val scope= rememberCoroutineScope()
    ScrollableTabRow(
        modifier=modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(
                    pagerState,tabPositions
                ),
                color = MaterialTheme.colors.primary
            )
        }
    ) {
        sections.forEachIndexed { index, section ->
            LeadingIconTab(
                icon = {},
                selected =pagerState.currentPage==index ,
                text = {
                    Text(
                        text=section.display_name,
                        style = MaterialTheme.typography.subtitle1
                    )
                },
                onClick = {
                    scope.launch {
                        viewModel.getNewsWireUseCases(NewsWireEvent.GetArticles("all",section.pathParam))
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }

    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SectionContentScreen(
    modifier: Modifier=Modifier,
    sections:List<Section>,
    pagerState: PagerState,
    newsWireState: NewsWireState
) {
    HorizontalPager(
        modifier = modifier,
        state=pagerState,
        count = sections.size
    ) {
        SectionContent(modifier =Modifier.fillMaxWidth() , newsWireState =newsWireState )
    }
}



@Composable
fun ArticleItem(
    article: ArticleEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp),
        elevation = 4.dp

    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(3f)
                ) {
                    Row() {
                        Image(
                            painter = painterResource(id = com.example.quiknews.R.drawable.poweredby_nytimes_30a),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = com.example.quiknews.R.string.ny_times_text),
                            modifier = Modifier.padding(start = 4.dp),
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(end = 4.dp),
                    )
                }
                if(article.thumbnail_standard!=null) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(article.thumbnail_standard)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.size(95.dp)
                        )
                    }
                }


            }
            Spacer(modifier = Modifier.height(8.dp))
            if(article.abstraction.isNotBlank()) {
                Text(
                    text = article.abstraction,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = getArticleDate(article.updated_date),
                    style = MaterialTheme.typography.caption
                )
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = ImageVector
                            .vectorResource(id = com.example.quiknews.R.drawable.ic_baseline_more_horiz_24),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text(
                    text = stringResource(id = com.example.quiknews.R.string.article_item_button_text),
                    style = MaterialTheme.typography.body1
                )
            }

        }


    }

}


@Composable
fun SectionContent(
    modifier: Modifier,
    newsWireState: NewsWireState
){
    Box(
        modifier=modifier
    ) {
        newsWireState.newsWireArticles?.let { articles ->
            LazyColumn() {
                itemsIndexed(articles) { index, article ->
                    ArticleItem(article = article)
                }
            }

        }
        if(newsWireState.isLoading){
            CircularProgressIndicator(
                modifier= Modifier.align(Alignment.Center)
            )
        }
    }

}

