package com.test.com.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @Provides
    fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences("appData", Context.MODE_PRIVATE)
    }
}