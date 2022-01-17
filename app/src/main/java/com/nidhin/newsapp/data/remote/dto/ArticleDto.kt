package com.nidhin.newsapp.data.remote.dto

import com.nidhin.newsapp.domain.model.Article

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String?
)

fun ArticleDto.toArticle(): Article {
    return Article(
        author =
        if (author.isNullOrEmpty())
            ""
        else
            author,
        title = title,
        content =
        if (content.isNullOrEmpty())
            ""
        else
            content,
        description =
        if (description.isNullOrEmpty())
            ""
        else
            description,
        publishedAt =
        if (publishedAt.isEmpty())
            ""
        else
            publishedAt,
        source = source?.name ?: "",
        url = if (url.isEmpty())
            ""
        else
            url,
        urlToImage = if (urlToImage.isNullOrEmpty())
            ""
        else
            urlToImage
    )
}