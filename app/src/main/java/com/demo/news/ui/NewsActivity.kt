package com.demo.news.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.news.R
import com.demo.news.factory.NewsViewModelFactory
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
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
        init()

        viewModel.newsList.observe(this, Observer {
        })

        viewModel.errorMessage.observe(this, Observer {
        })


    }

    private fun init(){
        if (Util.isNetworkConnected(this)) {
            viewModel.getLatestNewsList()
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}