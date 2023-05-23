package ru.sunpixel.mipt_android.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.sunpixel.mipt_android.data.db.AppDatabase
import ru.sunpixel.mipt_android.data.db.RestaurantDao

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).build()

    @Provides
    fun provideRestaurantDao(appDatabase: AppDatabase): RestaurantDao = appDatabase.restaurantDao()
}