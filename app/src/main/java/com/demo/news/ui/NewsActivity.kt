package com.demo.news.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.demo.news.R
import com.demo.news.factory.NewsViewModelFactory
import com.demo.news.ui.composables.HomeState
import com.demo.news.ui.viewmodel.NewsViewModel
import com.demo.news.utils.Util
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NewsActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            HomeContent()
        }
        init()
    }

    private fun init(){
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        if (Util.isNetworkConnected(this)) {
            viewModel.getLatestNewsList()
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    @Composable
    fun HomeContent(){
        val state = viewModel.state.value
        val page  = viewModel.page.value
        MaterialTheme() {
            HomeState(state = state, page = page, onCardClick = this::onNewsArticleClick, onScrollToBottom = this::onScrolledToBottom)
        }
    }

    private fun onNewsArticleClick(articleId : String){
        Toast.makeText(this,getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
    }

    private fun onScrolledToBottom(){
        viewModel.getNextPageData()
    }

}