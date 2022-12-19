package com.demo.news.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.demo.news.R
import com.demo.news.data.NewsScreenState
import com.demo.news.data.vo.Article
import com.demo.news.factory.NewsViewModelFactory
import com.demo.news.ui.composables.theme.*
import com.demo.news.ui.viewmodel.NewsViewModel
import com.demo.news.utils.Util
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HomeContent()
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        init()
    }

    private fun init(){
        if (Util.isNetworkConnected(this)) {
            viewModel.getLatestNewsList()
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun onCardClick(articleId : String){
        Toast.makeText(this,getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
    }


    @Composable
    fun HomeContent(){
        val state = viewModel.state.observeAsState().value
        MaterialTheme() {
            if (state != null) {
                HomeState(state = state, onCardClick = this::onCardClick)
            }
        }
    }

    @Composable
    fun HomeState(state: NewsScreenState, onCardClick: (String) -> Unit = {}) {
        when (state) {
            is NewsScreenState.LOADING -> CircularProgressIndicator()
            is NewsScreenState.SUCCESS -> NewsGallery(
                state.newsList,
                onNewsItemClick = onCardClick
            )
            is NewsScreenState.ERROR -> Text(state.errorMessage)
        }
    }

    @Composable
    fun NewsGallery(newsList: List<Article>, onNewsItemClick: (String) -> Unit){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(defaultPadding),
            modifier = Modifier.padding(defaultPadding, defaultPadding, defaultPadding, defaultPadding)
        ) {
            items(newsList.size) { index ->
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
                Column() {
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


}