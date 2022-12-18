package com.demo.news.di

import android.app.Application
import com.demo.news.NewsApplication
import com.demo.news.di.modules.ActivityBuildersModule
import com.demo.news.di.modules.DiBindingModule
import com.demo.news.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        DiBindingModule:: class,
        NetworkModule:: class
    ]
)
interface AppComponent: AndroidInjector<NewsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}