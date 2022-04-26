package com.farhanrv.iphonecatalogue.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farhanrv.iphonecatalogue.core.data.source.local.entity.DetailEntity
import com.farhanrv.iphonecatalogue.core.data.source.local.entity.IphoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IphoneDao {

    @Query("SELECT * FROM iphone_entity")
    fun getAllIphone(): Flow<List<IphoneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIphone(iphoneList: List<IphoneEntity>)

    @Query("SELECT * FROM detail_entity WHERE slug = :slug")
    fun getDetail(slug: String): Flow<List<DetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: List<DetailEntity>)

    @Query("SELECT * FROM iphone_entity WHERE is_favorite = 1")
    fun getAllFavorite(): Flow<List<IphoneEntity>>

    @Query("UPDATE iphone_entity SET is_favorite = :newState WHERE slug = :phoneSlug")
    fun updateFavorite(phoneSlug: String, newState: Boolean)
}