package com.mosalab.spacecraftisro.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mosalab.spacecraftisro.core.domain.usecase.SpacecraftUseCase

class FavoriteViewModel(spacecraftUseCase: SpacecraftUseCase): ViewModel() {
    val favoriteSpacecraft = spacecraftUseCase.getFavoriteSpacecraft().asLiveData()
}