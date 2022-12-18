package com.demo.news.data.source.remote

import com.demo.news.data.service.NewsRetrofitService
import com.demo.news.data.vo.NewsApiResponse
import com.demo.news.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val retrofitService: NewsRetrofitService): NewsRemoteDataSource {

    override suspend fun getNewsData(): Response<NewsApiResponse> {
        return retrofitService.getNews(Constants.newsType, Constants.fromDate, Constants.sortType, Constants.API_KEY, 10, 1)
    }

}