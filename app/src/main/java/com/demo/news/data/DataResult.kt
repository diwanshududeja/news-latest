package com.demo.news.data

import com.demo.news.data.vo.Article

sealed class DataResult {
    data class Success(val data: List<Article>?) : DataResult()
    data class Error(val error: String) : DataResult()
}