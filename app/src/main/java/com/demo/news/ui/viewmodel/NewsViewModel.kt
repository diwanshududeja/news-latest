package com.demo.news.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.news.data.DataResult
import com.demo.news.data.NewsScreenState
import com.demo.news.data.repositories.LatestNewsRepository
import com.demo.news.data.vo.Article
import com.demo.news.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: LatestNewsRepository) : ViewModel() {


    val state : MutableState<NewsScreenState> = mutableStateOf(NewsScreenState.LOADING)
    val page =  mutableStateOf(Constants.START_PAGE)
    private var newsArticles: List<Article> = listOf()

    fun getLatestNewsList(page: Int = Constants.START_PAGE) {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsDataFromAPI(page)
        }
    }

    fun getNextPageData(){
        incrementPage()
        getLatestNewsList(page.value)
    }

    private suspend fun getNewsDataFromAPI(currentPage: Int) {
        when (val dataResult = newsRepository.loadLatestNewsData(currentPage)) {
            is DataResult.Success -> {
                dataResult.data?.let {
                    newsArticles+=it
                    state.value = NewsScreenState.SUCCESS(newsArticles)
                }
            }
            is DataResult.Error -> {
                state.value = NewsScreenState.ERROR(dataResult.error)
            }
        }
    }

    private fun incrementPage(){
        page.value = page.value + 1
    }

}