package com.example.btccompose.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock

class CoroutinesModuleTest {

    @Test
    fun testProvideIoDispatcher() {
        val ioDispatcher: CoroutineDispatcher = CoroutinesModule.provideIoDispatcher()
        assertEquals(Dispatchers.IO, ioDispatcher)
    }

    @Test
    fun testProvideMainDispatcher() {
        val mainDispatcher: CoroutineDispatcher = CoroutinesModule.provideMainDispatcher()
        assertEquals(Dispatchers.Main, mainDispatcher)
    }

    @Test
    fun testProvideDefaultDispatcher() {
        val defaultDispatcher: CoroutineDispatcher = CoroutinesModule.provideDefaultDispatcher()
        assertEquals(Dispatchers.Default, defaultDispatcher)
    }
}
