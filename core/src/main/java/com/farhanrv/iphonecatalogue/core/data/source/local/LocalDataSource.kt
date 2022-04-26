package com.farhanrv.iphonecatalogue.core.data.source.local

import com.farhanrv.iphonecatalogue.core.data.source.local.entity.DetailEntity
import com.farhanrv.iphonecatalogue.core.data.source.local.entity.IphoneEntity
import com.farhanrv.iphonecatalogue.core.data.source.local.room.IphoneDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val iphoneDao: IphoneDao) {

    fun getAllIphone(): Flow<List<IphoneEntity>> = iphoneDao.getAllIphone()

    suspend fun insertIphone(iphoneList: List<IphoneEntity>) = iphoneDao.insertIphone(iphoneList)

    fun getDetail(slug: String): Flow<List<DetailEntity>> = iphoneDao.getDetail(slug)

    suspend fun insertDetail(detail: List<DetailEntity>) = iphoneDao.insertDetail(detail)

    fun getAllFavorite(): Flow<List<IphoneEntity>> = iphoneDao.getAllFavorite()

    fun setFavorite(phoneSlug: String, newState: Boolean) {
        iphoneDao.updateFavorite(phoneSlug, newState)
    }
}