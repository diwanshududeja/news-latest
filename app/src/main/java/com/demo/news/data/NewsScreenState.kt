package com.demo.news.data

import com.demo.news.data.vo.Article


sealed class NewsScreenState {
    object LOADING : NewsScreenState()
    data class ERROR(val errorMessage: String) : NewsScreenState()
    data class SUCCESS(val newsList: List<Article> = emptyList()) : NewsScreenState()
}