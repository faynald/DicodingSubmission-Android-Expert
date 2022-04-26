package com.farhanrv.iphonecatalogue.core.utils

import com.farhanrv.iphonecatalogue.core.data.source.local.entity.DetailEntity
import com.farhanrv.iphonecatalogue.core.data.source.remote.response.DetailResponse
import com.farhanrv.iphonecatalogue.core.domain.model.Detail

object DetailMapper {
    fun mapResponsesToEntities(input: List<DetailResponse>): List<DetailEntity> {
        val detailList = ArrayList<DetailEntity>()
        input.map {
            val iphone = DetailEntity(
                name = it.name,
                slug = it.slug,
                release = it.release,
                dimension = it.dimension,
                storage = it.storage,
                ram = it.ram,
                display = it.display,
                battery = it.battery,
                selfieCamera = it.selfieCamera,
                mainCamera = it.mainCamera,
                os = it.os,
                chipset = it.chipset,
                cpu = it.cpu,
                gpu = it.gpu,
                image = it.image
            )
            detailList.add(iphone)
        }
        return detailList
    }

    fun mapEntitiesToDomain(input: List<DetailEntity>): List<Detail> {
        val detailList = ArrayList<Detail>()

        input.map {
            val iphone = Detail(
                name = it.name,
                slug = it.slug,
                release = it.release,
                dimension = it.dimension,
                storage = it.storage,
                ram = it.ram,
                display = it.display,
                battery = it.battery,
                selfieCamera = it.selfieCamera,
                mainCamera = it.mainCamera,
                os = it.os,
                chipset = it.chipset,
                cpu = it.cpu,
                gpu = it.gpu,
                image = it.image
            )
            detailList.add(iphone)
        }
        return detailList
    }
}