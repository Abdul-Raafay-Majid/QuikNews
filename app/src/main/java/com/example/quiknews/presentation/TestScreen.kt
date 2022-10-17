package com.example.quiknews.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quiknews.domain.NewsWireUseCase
import com.example.quiknews.domain.model.ArticleDto

@Composable
fun TestScreen(
    viewModel:NewsWireViewModel= hiltViewModel()
){
    val state=viewModel.newsWireState.value

    Column() {
        Button(onClick = { viewModel.getNewsWireUseCases(NewsWireEvent.GetArticles("all","all")) } ){}
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            state.newsWireDto?.results?.let {
                items(state.newsWireDto.results) { article ->
                    TestItem(articleDto = article)
                }
            }

        }
    }

}



@Composable
fun TestItem(articleDto:ArticleDto){
    Card(
        modifier= Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = articleDto.title,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}