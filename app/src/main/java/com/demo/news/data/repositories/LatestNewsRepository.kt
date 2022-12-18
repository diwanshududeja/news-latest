package com.demo.news.data.repositories

import com.demo.news.data.DataResult


interface LatestNewsRepository {
    suspend fun loadLatestNewsData(): DataResult
}