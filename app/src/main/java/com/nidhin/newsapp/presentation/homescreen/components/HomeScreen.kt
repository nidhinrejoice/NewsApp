package com.nidhin.newsapp.presentation.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nidhin.newsapp.R
import com.nidhin.newsapp.domain.model.Article
import com.nidhin.newsapp.homescreen.viewmodel.MainViewModel
import java.util.*


private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onClick: (Article) -> Unit,
    onCategorySelect: (String) -> Unit
) {
//    Surface {
//        Text(
//            text = "Loading...",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            textAlign = TextAlign.Center,
//            fontSize = 20.sp,
//        )
    val viewState = viewModel.state.value
    Column {
//            if (viewState.isLoading) {
//
//            } else {
        Surface {
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.secondary).fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Top Headlines",
                        color = Color.White,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(5.dp)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        items(viewState.topHeadlines) {
                            Card(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier.padding(all = 4.dp)
                            ) {
                                TopHeadlineInfo(article = it, onClick = {
                                    onClick(it)
                                })
                            }
                        }
                    }
                }
//                    }
            }
        }
        val selectedCategory = viewState.selectedCategory
//        val articles by viewModel.catWiseArticles.observeAsState()
        val selectedIndex = viewState.categories.indexOfFirst { it == selectedCategory }
        Surface(color = MaterialTheme.colors.background) {
            Box {
                Column {
                    ScrollableTabRow(
                        selectedTabIndex = 0,
                        divider = {}, /* Disable the built-in divider */
                        edgePadding = 24.dp,
                        indicator = emptyTabIndicator,
                    ) {
                        viewState.categories.forEachIndexed { index, category ->
                            Tab(
                                selected = index == selectedIndex,
                                onClick = {
                                    onCategorySelect(category)
                                }) {
                                ChoiceChipContent(
                                    text = category.uppercase(Locale.getDefault()),
                                    selected = index == selectedIndex,
                                    modifier = Modifier.padding(
                                        horizontal = 4.dp,
                                        vertical = 16.dp
                                    )
                                )
                            }
                        }
                    }
                    if (!viewState.isLoading) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                        ) {
                            items(viewState.topCategoryHeadlines) {
                                Card(
                                    elevation = 4.dp,
                                    shape = RoundedCornerShape(15.dp),
                                    modifier = Modifier.padding(all = 4.dp)
                                ) {
                                    HeadlineInfo(article = it, onClick = {
                                        onClick(it)
                                    })
                                }
                            }
                        }
                    } else {
                        val composition by rememberLottieComposition(
                            spec = LottieCompositionSpec
                                .RawRes(R.raw.loading)
                        )
                        val progress by animateLottieCompositionAsState(
                            composition = composition,
                            isPlaying = true,
                            restartOnPlay = false

                        )
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LottieAnimation(
                                composition, progress,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
