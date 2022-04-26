package com.farhanrv.iphonecatalogue.di

import com.farhanrv.iphonecatalogue.core.domain.usecase.IphoneInteractor
import com.farhanrv.iphonecatalogue.core.domain.usecase.IphoneUseCase
import com.farhanrv.iphonecatalogue.ui.detail.DetailViewModel
import com.farhanrv.iphonecatalogue.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IphoneUseCase> { IphoneInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}