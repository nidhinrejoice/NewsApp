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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.nidhin.newsapp.domain.model.Article

@Composable
fun HeadlineInfo(article: Article, onClick: (Article) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick(article) }
            .defaultMinSize(10.dp, 120.dp)
            .padding(10.dp)
            .fillMaxSize()
    ) {

        article.urlToImage?.let {
            val image = rememberImagePainter(data = article.urlToImage,
                builder = { transformations() })
            Image(
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                alignment = Alignment.TopEnd,
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(100.dp, 100.dp)
            )
        }
        ConstraintLayout(
            modifier = Modifier
                .padding(5.dp)
                .weight(2.0f)
                .fillMaxSize()
        ) {
            // Create references for the composables to constrain
            val (title, source) = createRefs()
            Text(
                text = article.source,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primaryVariant,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .constrainAs(source) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Start,
                maxLines = 3, overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top, margin = 2.dp)
                        bottom.linkTo(source.top, margin = 2.dp)
                    }
                    .padding(5.dp)
                    .fillMaxWidth()
            )
        }
    }
}