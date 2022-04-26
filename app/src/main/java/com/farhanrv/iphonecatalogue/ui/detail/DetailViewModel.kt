package com.farhanrv.iphonecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.iphonecatalogue.core.domain.model.Iphone
import com.farhanrv.iphonecatalogue.core.domain.usecase.IphoneUseCase

class DetailViewModel(private val iphoneUseCase: IphoneUseCase) : ViewModel() {

    fun getDetail(slug: String) =
        iphoneUseCase.getDetail(slug).asLiveData()

    fun setFavorite(iphone: Iphone, newStatus: Boolean) =
        iphoneUseCase.setFavorite(iphone, newStatus)

}