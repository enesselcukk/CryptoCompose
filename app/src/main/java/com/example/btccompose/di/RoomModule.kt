package com.example.btccompose.di

import android.content.Context
import androidx.room.Room
import com.example.btccompose.data.database.BtcDao
import com.example.btccompose.data.database.BtcDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): BtcDatabase {
        return Room.databaseBuilder(context, BtcDatabase::class.java, "btc_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBtcDao(
        db: BtcDatabase,
    ): BtcDao = db.btcDao()

}