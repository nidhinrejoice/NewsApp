package com.nidhin.newsapp.domain.usecase.homescreen

import com.nidhin.newsapp.commons.Resource
import com.nidhin.newsapp.data.remote.dto.toArticle
import com.nidhin.newsapp.domain.model.Article
import com.nidhin.newsapp.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopHeadlines @Inject constructor(
    private val articlesRepository: ArticlesRepository
) {
    suspend operator fun invoke(category: String): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            val articles = articlesRepository.getTopHeadlines(category).map { it.toArticle() }
            emit(Resource.Success(articles))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet not available"))
        }
    }
}