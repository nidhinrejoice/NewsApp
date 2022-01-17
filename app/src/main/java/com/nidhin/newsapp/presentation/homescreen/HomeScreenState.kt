package com.nidhin.newsapp.presentation.homescreen

import com.nidhin.newsapp.domain.model.Article

data class HomeScreenState(
    val categories: List<String> = listOf(
        "business",
        "entertainment",
        "general",
        "health",
        "science",
        "sports",
        "technology"
    ),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val topHeadlines: List<Article> = listOf(),
    val topCategoryHeadlines: List<Article> = listOf()
)