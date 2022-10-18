package com.example.quiknews.presentation.NewsWire

import android.media.Image
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.quiknews.R
import com.example.quiknews.domain.model.ArticleDto
import com.example.quiknews.ui.theme.Orange
import com.example.quiknews.ui.theme.QuikNewsTheme

@Composable
fun NewsWireScreen(){

}


@Composable
fun ContentScreen(){

}

@Composable
fun ArticleItem(
    article:ArticleDto,
    modifier:Modifier=Modifier
){
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp),

    ){
        Column(
            modifier=Modifier.padding(8.dp)
        ){
            Row(
                modifier=Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier=Modifier.weight(3f)
                ) {
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.poweredby_nytimes_30a),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = R.string.ny_times_text) ,
                            modifier = Modifier.padding(start = 4.dp),
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.h5,
                        modifier=Modifier.padding(end = 4.dp)
                    )
                }
                Box(
                    modifier=Modifier.weight(1f)
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text=article.abstract,
                style =MaterialTheme.typography.subtitle2
            )
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text="Yesterday",
                    style = MaterialTheme.typography.caption
                )
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                   Icon(
                       imageVector = ImageVector
                           .vectorResource(id = R.drawable.ic_baseline_more_horiz_24),
                       contentDescription = null,
                   )
                }
            }
            Button(
                modifier=Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text(
                    text= stringResource(id = R.string.article_item_button_text),
                    style = MaterialTheme.typography.body1
                )
            }

        }


    }

}


@Preview
@Composable
fun PreviewScreen(){

    QuikNewsTheme(
    ) {
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