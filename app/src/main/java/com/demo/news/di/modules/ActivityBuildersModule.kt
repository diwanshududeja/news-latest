package com.demo.news.di.modules

import com.demo.news.ui.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMyActivity(): NewsActivity
}