package com.nidhin.newsapp.data.remote

import com.nidhin.newsapp.data.remote.dto.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines")
    suspend fun topHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 5
    ): ArticlesResponse
}