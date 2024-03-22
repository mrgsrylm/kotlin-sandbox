package id.my.mrgsrylm.myrepository.di

import android.content.Context
import id.my.mrgsrylm.myrepository.data.NewsRepository
import id.my.mrgsrylm.myrepository.data.local.room.NewsDatabase
import id.my.mrgsrylm.myrepository.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}