package com.farhanrv.iphonecatalogue.core.data.source.remote

import android.util.Log
import com.farhanrv.iphonecatalogue.core.data.source.remote.network.ApiResponse
import com.farhanrv.iphonecatalogue.core.data.source.remote.network.ApiService
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.DetailResponse
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.IphoneListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllPhone(): Flow<ApiResponse<List<IphoneListResponse>>> {
        return flow {
            try {
                val response = apiService.getPhoneList()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetail(slug: String): Flow<ApiResponse<List<DetailResponse>>> {
        return flow {
            try {
                val response = apiService.getDetail(slug)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}