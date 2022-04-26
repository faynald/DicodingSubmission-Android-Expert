package com.farhanrv.iphonecatalogue.core.domain.repository

import com.farhanrv.iphonecatalogue.core.data.Resource
import com.farhanrv.iphonecatalogue.core.domain.model.Detail
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone
import kotlinx.coroutines.flow.Flow

interface IIphoneRepository {

    fun getAllIphone(): Flow<Resource<List<Iphone>>>

    fun getDetail(slug: String): Flow<Resource<List<Detail>>>

    fun getAllFavorite(): Flow<List<Iphone>>

    fun setFavorite(iphone: Iphone, state: Boolean)
}