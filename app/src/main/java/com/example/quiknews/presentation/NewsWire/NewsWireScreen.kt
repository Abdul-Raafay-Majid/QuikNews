package com.example.quiknews.presentation.NewsWire

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.quiknews.R
import com.example.quiknews.domain.model.ArticleDto

@Composable
fun NewsWireScreen() {

}


@Composable
fun ContentScreen() {

}

@Composable
fun ArticleItem(
    article: ArticleDto,
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
                            painter = painterResource(id = R.drawable.poweredby_nytimes_30a),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(id = R.string.ny_times_text),
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.abstract,
                style = MaterialTheme.typography.subtitle2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Yesterday",
                    style = MaterialTheme.typography.caption
                )
                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = ImageVector
                            .vectorResource(id = R.drawable.ic_baseline_more_horiz_24),
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
                    text = stringResource(id = R.string.article_item_button_text),
                    style = MaterialTheme.typography.body1
                )
            }

        }


    }

}


