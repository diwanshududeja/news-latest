package com.demo.news.data.repositories

import com.demo.news.data.DataResult
import com.demo.news.data.source.remote.NewsRemoteDataSource
import com.demo.news.utils.Constants
import javax.inject.Inject

class LatestNewsRepositoryImpl @Inject constructor(private val remoteDataSource: NewsRemoteDataSource) : LatestNewsRepository{

    override suspend fun loadLatestNewsData(page: Int): DataResult {
        val response = remoteDataSource.getNewsData(page)
        return if (response.isSuccessful) {
            val responseData = response.body()
            if (responseData?.status == Constants.API_STATUS_SUCCESS){
                if(responseData.articles?.isNotEmpty() == true){
                    DataResult.Success(responseData.articles)
                }else{
                    DataResult.Error(Constants.MESSAGE_EMPTY_LIST)
                }
            } else{
                DataResult.Error(Constants.API_ISSUE)
            }
        } else {
            DataResult.Error(Constants.API_MAX_LIMIT_REACHED)
        }
    }

}