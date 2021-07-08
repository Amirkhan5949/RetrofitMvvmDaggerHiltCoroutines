package com.codeinger.postapp.di

import com.codeinger.postapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Provides
      fun provideBaseUrl()= "https://jsonplaceholder.typicode.com/"


    @Provides
    @Singleton
    fun provideApiService(url : String) : ApiService=
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
 }