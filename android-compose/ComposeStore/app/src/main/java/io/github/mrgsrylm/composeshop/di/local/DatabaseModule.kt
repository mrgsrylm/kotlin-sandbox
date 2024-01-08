package io.github.mrgsrylm.composeshop.di.local

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.mrgsrylm.composeshop.data.db.ShopDao
import io.github.mrgsrylm.composeshop.data.db.ShopDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(app: Application): ShopDatabase =
        Room.databaseBuilder(
            app,
            ShopDatabase::class.java,
            ShopDatabase.DATABASE_NAME
        )
            .build()

    @Singleton
    @Provides
    fun providesDao(db: ShopDatabase): ShopDao = db.dao
}