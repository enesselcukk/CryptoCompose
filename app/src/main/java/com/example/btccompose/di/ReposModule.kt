package com.example.btccompose.di


import com.example.btccompose.data.repository.BtcRepositoryImpl
import com.example.btccompose.data.repository.BtcRepositoryLocalImpl
import com.example.btccompose.domain.repository.BtcRepository
import com.example.btccompose.domain.repository.BtcRepositoryLocal
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface ModuleRepos {

    @Binds
    @Singleton
    fun btcRepository(repos: BtcRepositoryImpl): BtcRepository

    @Binds
    @Singleton
    fun reposLocalModule(repos: BtcRepositoryLocalImpl): BtcRepositoryLocal


}
