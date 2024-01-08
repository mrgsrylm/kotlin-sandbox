package io.github.mrgsrylm.composeshop.di.use_cases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import io.github.mrgsrylm.composeshop.domain.use_cases.local.GetSavedUserUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.local.SaveUserUseCase
import io.github.mrgsrylm.composeshop.domain.use_cases.remote.login.LoginUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {
    @Singleton
    @Provides
    fun providesLoginUseCase(repository: BackendRepository): LoginUseCase =
        LoginUseCase(repository)

    @Singleton
    @Provides
    fun providesSaveUserUseCase(repository: BackendRepository): SaveUserUseCase =
        SaveUserUseCase(repository)

    @Singleton
    @Provides
    fun providesGetSavedUserUseCase(repository: BackendRepository): GetSavedUserUseCase =
        GetSavedUserUseCase(repository)
}