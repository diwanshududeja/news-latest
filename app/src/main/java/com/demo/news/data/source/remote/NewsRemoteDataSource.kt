package com.demo.news.data.source.remote

import com.demo.news.data.vo.NewsApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getNewsData(page: Int): Response<NewsApiResponse>

}