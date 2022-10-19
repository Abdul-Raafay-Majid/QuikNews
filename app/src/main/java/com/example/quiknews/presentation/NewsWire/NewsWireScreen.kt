package com.example.quiknews.presentation.NewsWire

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.quiknews.R
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.presentation.NewsWireEvent
import com.example.quiknews.presentation.NewsWireState
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.presentation.utils.Section
import com.example.quiknews.presentation.utils.Sections
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsWireScreen(
    modifier: Modifier=Modifier,
    newsWireViewModel: NewsWireViewModel= hiltViewModel()
) {
  val pagerState= rememberPagerState()
  val uiState by newsWireViewModel.newsWireState

    Box(
        modifier = modifier
    ){
        Column(
            modifier=Modifier.fillMaxSize()
        ) {
            SectionTabs(
                modifier = Modifier.weight(1f),
                sections = Sections.sections ,
                pagerState =pagerState ,
                newsWireViewModel,

            )
            SectionContentScreen(
                modifier=Modifier.weight(17f),
                sections =Sections.sections ,
                pagerState =pagerState ,
                newsWireState =uiState,
            )
        }
    }
}









