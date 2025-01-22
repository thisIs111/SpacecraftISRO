package com.mosalab.spacecraftisro.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mosalab.spacecraftisro.core.domain.usecase.SpacecraftUseCase

class HomeViewModel(spacecraftUseCase: SpacecraftUseCase): ViewModel() {
    val spacecraft = spacecraftUseCase.getAllSpacecraft().asLiveData()
}