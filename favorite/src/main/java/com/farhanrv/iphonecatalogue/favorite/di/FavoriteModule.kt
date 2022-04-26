package com.farhanrv.iphonecatalogue.favorite.di

import com.farhanrv.iphonecatalogue.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}