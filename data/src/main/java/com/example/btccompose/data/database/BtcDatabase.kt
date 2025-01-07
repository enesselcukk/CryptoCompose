package com.example.btccompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.btccompose.domain.model.BtcResponseMapper

@Database(
    entities = [BtcResponseMapper::class],
    version = 1,
    exportSchema = false
)

abstract class BtcDatabase : RoomDatabase() {

    abstract fun btcDao(): BtcDao

    companion object {
        @Volatile
        private var INSTANCE: BtcDatabase? = null

        fun getDatabase(context: Context): BtcDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BtcDatabase::class.java, "btc_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}