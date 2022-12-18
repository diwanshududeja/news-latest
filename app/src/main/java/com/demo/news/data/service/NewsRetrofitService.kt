package com.demo.news.data.service

import com.demo.news.data.vo.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsRetrofitService {

    @GET("/v2/everything")
    suspend fun getNews(@Query("q") q: String, @Query("from") from: String, @Query("sortBy") sortBy: String, @Query("apiKey") apiKey: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Response<NewsApiResponse>


}