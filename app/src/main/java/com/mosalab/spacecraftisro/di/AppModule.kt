package com.mosalab.spacecraftisro.di

import com.mosalab.spacecraftisro.core.domain.usecase.SpacecraftInteractor
import com.mosalab.spacecraftisro.core.domain.usecase.SpacecraftUseCase
import com.mosalab.spacecraftisro.detail.DetailSpacecraftViewModel
import com.mosalab.spacecraftisro.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<SpacecraftUseCase> { SpacecraftInteractor(get()) }
}

val viewModelModule = module {
    viewModel{ HomeViewModel(get())}
    viewModel { DetailSpacecraftViewModel(get()) }

}