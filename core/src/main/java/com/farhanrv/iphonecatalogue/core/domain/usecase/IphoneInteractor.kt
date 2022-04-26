package com.farhanrv.iphonecatalogue.core.domain.usecase

import com.farhanrv.iphonecatalogue.core.data.Resource
import com.farhanrv.iphonecatalogue.core.domain.model.Detail
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone
import com.farhanrv.iphonecatalogue.core.domain.repository.IIphoneRepository
import kotlinx.coroutines.flow.Flow

class IphoneInteractor(private val repository: IIphoneRepository) : IphoneUseCase {

    override fun getAllIphone(): Flow<Resource<List<Iphone>>> =
        repository.getAllIphone()

    override fun getDetail(slug: String): Flow<Resource<List<Detail>>> =
        repository.getDetail(slug)

    override fun getAllFavorite(): Flow<List<Iphone>> =
        repository.getAllFavorite()

    override fun setFavorite(iphone: Iphone, state: Boolean) =
        repository.setFavorite(iphone, state)
}