package com.demo.news.data.vo

import androidx.annotation.Keep

/**
 * Modal class to handle the news api response
 */
@Keep
data class NewsApiResponse (
    var status: String? = null,
    var totalResults: Int = 0,
    var articles : ArrayList<Article>? = null
)


