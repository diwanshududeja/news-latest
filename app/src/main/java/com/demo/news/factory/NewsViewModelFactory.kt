package com.demo.news.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.news.data.repositories.LatestNewsRepository
import com.demo.news.ui.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(private val newsRepo: LatestNewsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            NewsViewModel(newsRepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}