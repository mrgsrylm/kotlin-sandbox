package io.github.mrgsrylm.composeshop.di.use_cases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import io.github.mrgsrylm.composeshop.domain.use_cases.local.DeleteFromFavoritesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.GetFavoritesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.SaveToFavoritesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.product.GetProductsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductUseCaseModule {
    @Singleton
    @Provides
    fun providesSaveToFavoritesUseCase(repository: BackendRepository): SaveToFavoritesUseCase =
        SaveToFavoritesUseCase(repository)

    @Singleton
    @Provides
    fun providesGetFavoritesUseCase(repository: BackendRepository): GetFavoritesUseCase =
        GetFavoritesUseCase(repository)

    @Singleton
    @Provides
    fun providesDeleteFromFavoritesUseCase(repository: BackendRepository): DeleteFromFavoritesUseCase =
        DeleteFromFavoritesUseCase(repository)

    @Singleton
    @Provides
    fun providesGetProductsUseCase(repository: BackendRepository): GetProductsUseCase =
        GetProductsUseCase(repository)
}