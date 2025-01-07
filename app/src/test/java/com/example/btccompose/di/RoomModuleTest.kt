package com.example.btccompose.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.btccompose.data.database.BtcDao
import com.example.btccompose.data.database.BtcDatabase
import io.mockk.*
import org.junit.Before
import org.junit.Test

class RoomModuleTest {

    private lateinit var mockContext: Context
    private lateinit var mockDatabase: BtcDatabase
    private lateinit var mockDao: BtcDao

    @Before
    fun setUp() {
        mockContext = mockk(relaxed = true)
        mockDatabase = mockk()
        mockDao = mockk()

        mockkStatic(Room::class)
        val mockBuilder = mockk<RoomDatabase.Builder<BtcDatabase>>()

        every {
            Room.databaseBuilder(mockContext, BtcDatabase::class.java, "btc_database")
        } returns mockBuilder

        every { mockBuilder.fallbackToDestructiveMigration() } returns mockBuilder
        every { mockBuilder.build() } returns mockDatabase
    }

    @Test
    fun testProvideAppDatabase() {
        val database = RoomModule.provideAppDatabase(mockContext)

        assert(database == mockDatabase)
    }

    @Test
    fun testProvideBtcDao() {
        every { mockDatabase.btcDao() } returns mockDao

        val dao = RoomModule.provideBtcDao(mockDatabase)

        assert(dao == mockDao)
    }
}
