package com.nidhin.newsapp.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.nidhin.newsapp.domain.model.Article

@Composable
fun TopHeadlineInfo(article: Article, onClick: (Article) -> Unit) {
    Column(modifier = Modifier.clickable { onClick(article) }) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .padding(10.dp)
                .defaultMinSize(10.dp, 320.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                article.urlToImage?.let {
                    val image = rememberImagePainter(data = article.urlToImage,
                        builder = { transformations(RoundedCornersTransformation()) })
                    Image(
                        painter = image,
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        alignment = Alignment.TopEnd,
                        modifier = Modifier.height(180.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                article.source.let {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

            }
        }
    }
}