package com.jujodevs.habitsappcourse.home.api.data.di

import android.content.Context
import androidx.room.Room
import com.jujodevs.habitsappcourse.home.api.data.alarm.AlarmHandlerImpl
import com.jujodevs.habitsappcourse.home.api.data.local.HomeDatabase
import com.jujodevs.habitsappcourse.home.api.data.local.typeconverter.HomeTypeConverter
import com.jujodevs.habitsappcourse.home.api.data.remote.HomeApi
import com.jujodevs.habitsappcourse.home.api.data.repository.HomeRepositoryImpl
import com.jujodevs.habitsappcourse.home.api.domain.alarm.AlarmHandler
import com.jujodevs.habitsappcourse.home.api.domain.repository.HomeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

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
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Singleton
    @Provides
    fun provideHomeApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ) =
        Retrofit.Builder()
            .baseUrl(HomeApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create<HomeApi>()

    @Singleton
    @Provides
    fun provideAlarmHandler(alarmHandlerImpl: AlarmHandlerImpl): AlarmHandler = alarmHandlerImpl
}

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository =
        homeRepositoryImpl
}