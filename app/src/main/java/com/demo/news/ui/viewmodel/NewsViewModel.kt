package com.demo.news.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.news.data.DataResult
import com.demo.news.data.NewsScreenState
import com.demo.news.data.repositories.LatestNewsRepository
import com.demo.news.data.vo.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: LatestNewsRepository) : ViewModel() {


    val state =  MutableLiveData<NewsScreenState>()

    fun getLatestNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsDataFromAPI()
        }

    }

    private suspend fun getNewsDataFromAPI() {
        state.postValue(NewsScreenState.LOADING)
        when (val dataResult = newsRepository.loadLatestNewsData()) {
            is DataResult.Success -> {
                state.postValue(dataResult.data?.let { NewsScreenState.SUCCESS(it) })
            }
            is DataResult.Error -> {
                state.postValue(NewsScreenState.ERROR(dataResult.error))
            }
        }
    }

}