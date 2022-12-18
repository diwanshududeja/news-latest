package com.demo.news.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.news.data.repositories.LatestNewsRepository
import com.demo.news.data.repositories.LatestNewsRepositoryImpl
import com.demo.news.data.source.remote.NewsRemoteDataSource
import com.demo.news.data.source.remote.NewsRemoteDataSourceImpl
import com.demo.news.factory.NewsViewModelFactory
import com.demo.news.ui.viewmodel.NewsViewModel
import com.demo.news.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DiBindingModule {

    @Binds
    abstract fun bindsRepository(repository: LatestNewsRepositoryImpl): LatestNewsRepository

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: NewsRemoteDataSourceImpl): NewsRemoteDataSource

    @Binds
    abstract fun bindViewModelFactory(factory: NewsViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun providesViewModel(viewModel: NewsViewModel): ViewModel


}