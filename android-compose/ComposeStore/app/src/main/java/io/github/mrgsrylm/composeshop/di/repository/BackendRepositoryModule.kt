package io.github.mrgsrylm.composeshop.di.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mrgsrylm.composeshop.data.db.ShopDao
import io.github.mrgsrylm.composeshop.data.remote.BackendService
import io.github.mrgsrylm.composeshop.data.repository.BackendRepositoryImpl
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BackendRepositoryModule {
    @Singleton
    @Provides
    fun provideBackendRepository(
        api: BackendService,
        dao: ShopDao
    ): BackendRepository = BackendRepositoryImpl(api, dao)
}