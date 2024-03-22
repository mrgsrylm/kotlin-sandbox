package id.my.mrgsrylm.myrepository.ui

import androidx.lifecycle.ViewModel
import id.my.mrgsrylm.myrepository.data.NewsRepository
import id.my.mrgsrylm.myrepository.data.local.entity.NewsEntity

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()

    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()

    fun saveNews(news: NewsEntity) {
        newsRepository.setBookmarkedNews(news, true)
    }

    fun deleteNews(news: NewsEntity) {
        newsRepository.setBookmarkedNews(news, false)
    }
}