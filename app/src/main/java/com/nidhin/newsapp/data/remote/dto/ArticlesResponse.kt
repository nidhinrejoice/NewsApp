package com.nidhin.newsapp.data.remote.dto

data class ArticlesResponse(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)