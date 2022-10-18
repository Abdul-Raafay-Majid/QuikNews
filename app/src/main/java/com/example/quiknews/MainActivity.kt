package com.example.quiknews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.presentation.NewsWire.ArticleItem
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.presentation.TestScreen
import com.example.quiknews.ui.theme.QuikNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            QuikNewsTheme {
                // A surface container using the 'background' color from the theme

                    ArticleItem(
                        article = ArticleDto(
                            section = "arts",
                            subsection = "arts",
                            title = "\"The Designer Exploring African Stories Through Traditional Fabrics\"",
                            uri = "nyt://article/9086ac3c-a152-5594-8891-f0afa3d9129d",
                            url = "https://www.nytimes.com/2022/10/17/style/bubu-ogisi-iamsigo.html",
                            abstract = "Bubu Ogisi travels around Africa, resurrecting artisanal skills that have been in existence for years to explore sustainability and identity.",
                            byline = "BY UGONNA-ORA OWOH",
                            thumbnail_standard = "https://static01.nyt.com/images/2022/10/13/multimedia/RF-Iamsigo-1/RF-Iamsigo-1-thumbStandard.jpg",
                            item_type = "Article",
                            source = "New York Times",
                            updated_date = "2022-10-17T09:00:14-04:00",
                            material_type_facet = "News",
                            kicker = "Responsible Fashion",
                            subheadline = ""
                        )
                    )
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