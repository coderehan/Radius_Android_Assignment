package com.rehan.radiusandroidassignment.di

import com.rehan.radiusandroidassignment.api.FacilitiesAPI
import com.rehan.radiusandroidassignment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFacilitiesAPI(retrofit: Retrofit): FacilitiesAPI {
        return retrofit.create(FacilitiesAPI::class.java)
    }
}