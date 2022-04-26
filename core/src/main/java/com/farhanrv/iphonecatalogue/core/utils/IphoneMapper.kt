package com.farhanrv.iphonecatalogue.core.utils

import com.farhanrv.iphonecatalogue.core.data.source.local.entity.IphoneEntity
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.IphoneListResponse
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone

object IphoneMapper {
    fun mapResponsesToEntities(input: List<IphoneListResponse>): List<IphoneEntity> {
        val iphoneList = ArrayList<IphoneEntity>()
        input.map {
            val iphone = IphoneEntity(
                name = it.name,
                slug = it.slug,
                image = it.image,
                isFavorite = false
            )
            iphoneList.add(iphone)
        }
        return iphoneList
    }

    fun mapEntitiesToDomain(input: List<IphoneEntity>): List<Iphone> {
        val iphoneList = ArrayList<Iphone>()

        input.map {
            val iphone = Iphone(
                name = it.name,
                slug = it.slug,
                image = it.image,
                isFavorite = it.isFavorite
            )
            iphoneList.add(iphone)
        }
        return iphoneList
    }
}