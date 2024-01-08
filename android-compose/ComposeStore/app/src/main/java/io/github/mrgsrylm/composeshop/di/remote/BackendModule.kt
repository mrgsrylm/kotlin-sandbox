package io.github.mrgsrylm.composeshop.di.remote

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mrgsrylm.composeshop.data.remote.BackendService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BackendModule {
    @Singleton
    @Provides
    fun providesDummyJsonService(app: Application): BackendService =
        Retrofit.Builder()
            .baseUrl(BackendService.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BackendService::class.java)
}