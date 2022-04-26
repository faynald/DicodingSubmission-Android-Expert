package com.farhanrv.iphonecatalogue.core.data

import com.farhanrv.iphonecatalogue.core.data.source.local.LocalDataSource
import com.farhanrv.iphonecatalogue.core.data.source.remote.RemoteDataSource
import com.farhanrv.iphonecatalogue.core.data.source.remote.network.ApiResponse
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.DetailResponse
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.IphoneListResponse
import com.farhanrv.iphonecatalogue.core.domain.model.Detail
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone
import com.farhanrv.iphonecatalogue.core.domain.repository.IIphoneRepository
import com.farhanrv.iphonecatalogue.core.utils.AppExecutors
import com.farhanrv.iphonecatalogue.core.utils.DetailMapper
import com.farhanrv.iphonecatalogue.core.utils.IphoneMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IphoneRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IIphoneRepository {
    override fun getAllIphone(): Flow<Resource<List<Iphone>>> =
        object : NetworkBoundResource<List<Iphone>, List<IphoneListResponse>>() {
            override fun loadFromDB(): Flow<List<Iphone>> =
                localDataSource.getAllIphone().map {
                    IphoneMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Iphone>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<IphoneListResponse>>> =
                remoteDataSource.getAllPhone()

            override suspend fun saveCallResult(data: List<IphoneListResponse>) {
                val iphoneList = IphoneMapper.mapResponsesToEntities(data)
                localDataSource.insertIphone(iphoneList)
            }
        }.asFlow()

    override fun getDetail(slug: String): Flow<Resource<List<Detail>>> =
        object : NetworkBoundResource<List<Detail>, List<DetailResponse>>() {
            override fun loadFromDB(): Flow<List<Detail>> =
                localDataSource.getDetail(slug).map {
                    DetailMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Detail>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DetailResponse>>> =
                remoteDataSource.getDetail(slug)

            override suspend fun saveCallResult(data: List<DetailResponse>) {
                val detailList = DetailMapper.mapResponsesToEntities(data)
                localDataSource.insertDetail(detailList)
            }

        }.asFlow()

    override fun getAllFavorite(): Flow<List<Iphone>> =
        localDataSource.getAllFavorite().map {
            IphoneMapper.mapEntitiesToDomain(it)
        }

    override fun setFavorite(iphone: Iphone, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavorite(iphone.slug, state) }
    }
}