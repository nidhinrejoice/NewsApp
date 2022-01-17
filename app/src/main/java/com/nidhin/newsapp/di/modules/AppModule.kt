package com.nidhin.newsapp.di.modules

import com.nidhin.newsapp.api.HttpClient
import com.nidhin.newsapp.api.LoggingInterceptor
import com.nidhin.newsapp.commons.Constants
import com.nidhin.newsapp.data.remote.ApiService
import com.nidhin.newsapp.data.repository.ArticleRepositoryImpl
import com.nidhin.newsapp.domain.repository.ArticlesRepository
import com.nidhin.newsapp.persistance.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
open class AppModule {

    @Provides
    open fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    open fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    open fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    open fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("baseUrl") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("baseUrl")
    open fun provideBaseUrl(): String = Constants.API_END_POINT


    @Provides
    @Singleton
    fun provideArticleRepository(
        apiService: ApiService,
        sharedPrefsHelper: SharedPrefsHelper
    ): ArticlesRepository {
        return ArticleRepositoryImpl(apiService, sharedPrefsHelper)
    }
}