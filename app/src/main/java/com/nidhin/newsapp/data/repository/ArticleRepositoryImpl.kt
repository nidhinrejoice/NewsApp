package com.nidhin.newsapp.data.repository

import com.nidhin.newsapp.commons.Constants
import com.nidhin.newsapp.data.remote.ApiService
import com.nidhin.newsapp.domain.repository.ArticlesRepository
import com.nidhin.newsapp.persistance.SharedPrefsHelper
import com.nidhin.newsapp.data.remote.dto.ArticleDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class ArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPrefsHelper: SharedPrefsHelper
) : ArticlesRepository {


    fun List<String>.chooseRandomItem(): String {
        val randomNum = Random.nextInt(0, this.size)
        return this[randomNum]
    }

    override suspend fun getTopHeadlines(category: String): List<ArticleDto> {

        var pageSize = if (category.isNotEmpty())
            10
        else 5
        return apiService.topHeadlines(
            Constants.API_KEY,
            "in",
            category = category,
            pageSize = pageSize
        ).articles
    }
}