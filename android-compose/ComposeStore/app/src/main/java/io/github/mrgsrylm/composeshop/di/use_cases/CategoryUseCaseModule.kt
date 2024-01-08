package io.github.mrgsrylm.composeshop.di.use_cases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.category.GetCategoriesUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.category.GetCategoryUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryUseCaseModule {
    @Singleton
    @Provides
    fun providesGetCategoriesUseCase(repository: BackendRepository): GetCategoriesUseCase =
        GetCategoriesUseCase(repository)

    @Singleton
    @Provides
    fun providesGetCategoryUseCase(repository: BackendRepository): GetCategoryUseCase =
        GetCategoryUseCase(repository)
}