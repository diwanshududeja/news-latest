package com.demo.news.data.vo

import androidx.annotation.Keep

@Keep
data class Article(
    var source: ArticleSource? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
)
