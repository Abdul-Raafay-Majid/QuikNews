package com.example.quiknews

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.presentation.NewsWire.ArticleItem
import com.example.quiknews.presentation.NewsWire.NewsWireScreen
import com.example.quiknews.presentation.NewsWireEvent
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.ui.theme.QuikNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel:NewsWireViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("Clean Up","onDestroy")
            viewModel.getNewsWireUseCases(NewsWireEvent.ClearAllArticles)
            QuikNewsTheme {
                // A surface container using the 'background' color from the theme
                    NewsWireScreen()
                }
            }
        }


}




@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuikNewsTheme {
        Greeting("Android")
    }
}