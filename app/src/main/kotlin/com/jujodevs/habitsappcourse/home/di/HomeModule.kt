package com.jujodevs.habitsappcourse.home.di

import android.content.Context
import androidx.room.Room
import com.jujodevs.habitsappcourse.home.data.local.HomeDatabase
import com.jujodevs.habitsappcourse.home.data.local.typeconverter.HomeTypeConverter
import com.jujodevs.habitsappcourse.home.data.repository.HomeRepositoryImpl
import com.jujodevs.habitsappcourse.home.domain.repository.HomeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository =
        homeRepositoryImpl

    @Provides
    @Singleton
    fun provideHabitDatabase(
        @ApplicationContext context: Context,
        homeTypeConverter: HomeTypeConverter,
    ): HomeDatabase =
        Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(homeTypeConverter)
            .build()

    @Singleton
    @Provides
    fun provideHabitDao(database: HomeDatabase) = database.habitDao()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()
}