package com.farhanrv.iphonecatalogue.core.data.source.remote.network

import com.farhanrv.iphonecatalogue.core.data.source.remote.response.DetailResponse
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.IphoneListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getPhoneList(): List<IphoneListResponse>

    @GET("detail/{iphone_slug}")
    suspend fun getDetail(@Path("iphone_slug") slug: String): List<DetailResponse>
}