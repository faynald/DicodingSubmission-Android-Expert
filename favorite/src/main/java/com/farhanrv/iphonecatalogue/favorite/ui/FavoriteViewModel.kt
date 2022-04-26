package com.farhanrv.iphonecatalogue.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.iphonecatalogue.core.domain.usecase.IphoneUseCase

class FavoriteViewModel(iphoneUseCase: IphoneUseCase) : ViewModel() {

    val favorite = iphoneUseCase.getAllFavorite().asLiveData()

}