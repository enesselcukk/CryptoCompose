package com.example.btccompose.data.repository

import com.example.btccompose.data.database.BtcDao
import com.example.btccompose.domain.model.BtcResponseMapper
import com.example.btccompose.utils.NetworkState
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BtcRepositoryLocalImplTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var btcDao: BtcDao
    private lateinit var btcRepositoryLocalImpl: BtcRepositoryLocalImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        btcDao = mockk()
        btcRepositoryLocalImpl = BtcRepositoryLocalImpl(btcDao, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetLocalBtcSuccess() = runBlocking {
        val mockBtcList = listOf(BtcResponseMapper(id = 1, isFavorite = false))
        coEvery { btcDao.allBtc() } returns mockBtcList

        val result = btcRepositoryLocalImpl.getLocalBtc()
            .first { it is NetworkState.Success }

        assertTrue(result is NetworkState.Success)
        assertEquals(mockBtcList, (result as NetworkState.Success).data)
        coVerify(exactly = 1) { btcDao.allBtc() }
    }

    @Test
    fun testGetLocalBtcError() = runBlocking {
        val exception = Exception("Database error")
        coEvery { btcDao.allBtc() } throws exception

        val result = btcRepositoryLocalImpl.getLocalBtc()
            .first { it is NetworkState.Error }

        assertTrue(result is NetworkState.Error)
        assertEquals(exception.message, (result as NetworkState.Error).message?.localizedMessage)
        coVerify(exactly = 1) { btcDao.allBtc() }
    }

    @Test
    fun testInsertBtcFavorite() = runBlocking {
        val btcResponseMapper = BtcResponseMapper(id = 1, isFavorite = false)
        val existingItem = BtcResponseMapper(id = 1, isFavorite = true)
        coEvery { btcDao.getById(btcResponseMapper.id) } returns existingItem
        coEvery {
            btcDao.updateFavoriteStatus(
                btcResponseMapper.id,
                !existingItem.isFavorite
            )
        } returns 1

        val result =
            btcRepositoryLocalImpl.insertBtc(btcResponseMapper).first { it is NetworkState.Success }

        assertTrue(result is NetworkState.Success)
        assertEquals("Favorite status updated", (result as NetworkState.Success).data)
        coVerify(exactly = 1) { btcDao.getById(btcResponseMapper.id) }
        coVerify(exactly = 1) {
            btcDao.updateFavoriteStatus(
                btcResponseMapper.id,
                !existingItem.isFavorite
            )
        }
    }

    @Test
    fun testInsertBtcFavorites() = runBlocking {
        val btcResponseMapper = BtcResponseMapper(id = 2, isFavorite = false)
        coEvery { btcDao.getById(btcResponseMapper.id) } returns null
        coEvery { btcDao.insertBtc(btcResponseMapper) } returns 1L

        val result = btcRepositoryLocalImpl.insertBtc(btcResponseMapper).first { it is NetworkState.Success }

        assertTrue(result is NetworkState.Success)
        assertEquals("Item added to favorites", (result as NetworkState.Success).data)
        coVerify(exactly = 1) { btcDao.getById(btcResponseMapper.id) }
        coVerify(exactly = 1) { btcDao.insertBtc(btcResponseMapper) }
    }

    @Test
    fun testDeleteAllDeleteAll() = runBlocking {
        coEvery { btcDao.deleteAll() } just Runs

        val result = btcRepositoryLocalImpl.deleteAll().first { it is NetworkState.Success }

        assertTrue(result is NetworkState.Success)
        assertEquals(Unit, (result as NetworkState.Success).data)
        coVerify(exactly = 1) { btcDao.deleteAll() }
    }

    @Test
    fun testDeleteAllError() = runBlocking {
        val exception = Exception("Database error")
        coEvery { btcDao.deleteAll() } throws exception

        val result = btcRepositoryLocalImpl.deleteAll().first { it is NetworkState.Error }

        assertTrue(result is NetworkState.Error)
        assertEquals(exception.message, (result as NetworkState.Error).message?.localizedMessage)
        coVerify(exactly = 1) { btcDao.deleteAll() }
    }
}
