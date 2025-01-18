package com.example.btccompose.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.btccompose.domain.model.BtcResponseMapper

@Dao
interface BtcDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBtc(btcResponseMapper: BtcResponseMapper):Long

    @Query("DELETE FROM btc_entity WHERE id= :idBtc")
    suspend fun deleteID(idBtc: Int): Int

    @Query("SELECT * FROM btc_entity")
    suspend fun allBtc(): List<BtcResponseMapper>

    @Query("SELECT * FROM btc_entity WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): BtcResponseMapper?

    @Query("SELECT * FROM btc_entity WHERE id IN (:ids)")
    suspend fun getFavoritesByIds(ids: List<Int>): List<BtcResponseMapper>

    @Query("UPDATE btc_entity SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean): Int

    @Query("DELETE FROM btc_entity")
    suspend fun deleteAll()
}