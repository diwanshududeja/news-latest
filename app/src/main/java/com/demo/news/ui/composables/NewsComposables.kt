package com.demo.news.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.demo.news.R
import com.demo.news.data.NewsScreenState
import com.demo.news.data.vo.Article
import com.demo.news.ui.composables.theme.*
import com.demo.news.utils.Constants

@Composable
fun HomeState(
    state: NewsScreenState,
    onCardClick: (String) -> Unit = {},
    page: Int,
    onScrollToBottom:() -> Unit = {}
) {
    when (state) {
        is NewsScreenState.LOADING -> CircularProgressIndicator()
        is NewsScreenState.SUCCESS -> NewsGallery(
            state.newsList,
            page,
            onNewsItemClick = onCardClick,
            onCallNextPage = onScrollToBottom
        )
        is NewsScreenState.ERROR -> Text(state.errorMessage)
    }
}

@Composable
fun NewsGallery(
    newsList: List<Article>,
    page: Int,
    onNewsItemClick: (String) -> Unit,
    onCallNextPage: () -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(defaultPadding),
        modifier = Modifier.padding(defaultPadding, defaultPadding, defaultPadding, defaultPadding)
    ) {
        items(newsList.size) { index ->
            if((index + 1) >= (page * Constants.PAGE_SIZE)){
                onCallNextPage()
            }
            NewsItem(newsList[index], onNewsItemClick)
        }
    }
}

@Composable
fun NewsItem(article: Article, onNewsItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .clickable(enabled = true) {
                onNewsItemClick(article.source?.id.toString())
            }
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .height(imageHeight),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = article.author,
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.error_placeholder)
            )
            Spacer(modifier = Modifier.height(paddingSmall))
            article.title?.let {
                Text(text = it,
                    style = Typography.body2,
                    modifier = Modifier.padding(defaultPadding, zeroPadding, defaultPadding, defaultPadding)
                )
            }
        }
    }


}