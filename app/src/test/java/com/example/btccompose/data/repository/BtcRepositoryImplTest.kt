package com.example.btccompose.data.repository
import com.example.btccompose.data.api.BtcApi
import com.example.btccompose.data.api.BtcGraphApi
import com.example.btccompose.data.model.BTCDataResponse
import com.example.btccompose.data.model.BtcDataResponseGraph
import com.example.btccompose.utils.NetworkState
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import retrofit2.Response

class BtcRepositoryImplTest {

    private lateinit var btcApi: BtcApi
    private lateinit var btcGraphApi: BtcGraphApi
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var btcRepository: BtcRepositoryImpl

    @Before
    fun setUp() {
        btcApi = mockk()
        btcGraphApi = mockk()
        dispatcher = Dispatchers.Unconfined
        btcRepository = BtcRepositoryImpl(btcApi, btcGraphApi, dispatcher)
    }

    @Test
    fun testGetBtcSuccess() = runTest {
        val expectedResponse = BTCDataResponse()
        coEvery { btcApi.getBtc() } returns expectedResponse


        val result = btcRepository.getBtc().toList()

        assertEquals(3, result.size)
        assertTrue(result[1] is NetworkState.Success)
        assertEquals(expectedResponse, (result[1] as NetworkState.Success).data)
        coVerify { btcApi.getBtc() }
    }

    @Test
    fun testGetBtcError() = runTest {
        val exception = Exception("Network error")
        coEvery { btcApi.getBtc() } throws exception

        val result = btcRepository.getBtc().toList()

        assertEquals(3, result.size)
        assertTrue(result[2] is NetworkState.Error)
        assertEquals(exception, (result[2] as NetworkState.Error).message?.localizedMessage)
        coVerify { btcApi.getBtc() }
    }

    @Test
    fun testGetBtcDetailSuccess() = runTest {
        val expectedResponse = BtcDataResponseGraph()
        coEvery { btcGraphApi.getBtcDetail(any(), any(), any(), any()) } returns expectedResponse

        val result = btcRepository.getBtcDetail(0, 1, "BTC", 1).toList()

        assertEquals(3, result.size)
        assertTrue(result[1] is NetworkState.Success)
        assertEquals(expectedResponse, (result[1] as NetworkState.Success).data)
        coVerify { btcGraphApi.getBtcDetail(any(), any(), any(), any()) }
    }

    @Test
    fun testGetBtcDetailError() = runTest {
        val exception = Exception("Network error")
        coEvery { btcGraphApi.getBtcDetail(any(), any(), any(), any()) } throws exception

        val result = btcRepository.getBtcDetail(0, 1, "BTC", 1).toList().first {
            it is NetworkState.Error
        }

        assertTrue(result is NetworkState.Error)
        assertEquals(exception.localizedMessage, (result as NetworkState.Error).message?.localizedMessage)
        coVerify { btcGraphApi.getBtcDetail(any(), any(), any(), any()) }
    }
}
