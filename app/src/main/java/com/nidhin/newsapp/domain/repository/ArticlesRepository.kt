package com.nidhin.newsapp.domain.repository

import com.nidhin.newsapp.data.remote.dto.ArticleDto
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun getTopHeadlines(category: String): List<ArticleDto>
}