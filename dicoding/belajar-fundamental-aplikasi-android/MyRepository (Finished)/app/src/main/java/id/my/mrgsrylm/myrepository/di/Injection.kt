package id.my.mrgsrylm.myrepository.di

import android.content.Context
import id.my.mrgsrylm.myrepository.data.NewsRepository
import id.my.mrgsrylm.myrepository.data.local.room.NewsDatabase
import id.my.mrgsrylm.myrepository.data.remote.retrofit.ApiConfig
import id.my.mrgsrylm.myrepository.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(apiService, dao, appExecutors)
    }
}