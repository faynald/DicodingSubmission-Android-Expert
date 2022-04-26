package com.farhanrv.iphonecatalogue.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.iphonecatalogue.core.domain.usecase.IphoneUseCase

class HomeViewModel(iphoneUseCase: IphoneUseCase) : ViewModel() {

    val iphone = iphoneUseCase.getAllIphone().asLiveData()

}