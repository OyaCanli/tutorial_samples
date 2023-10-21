package com.canlioya.dynamicfontsizes

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providePreferences(
        @ApplicationContext context: Context,
        ): AppPreferences {
        val preferences = context.getSharedPreferences("app-preferences", Context.MODE_PRIVATE)
        return AppPreferences(preferences)
    }
}