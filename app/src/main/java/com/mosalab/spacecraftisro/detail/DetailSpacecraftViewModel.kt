package com.mosalab.spacecraftisro.detail

import androidx.lifecycle.ViewModel
import com.mosalab.spacecraftisro.core.domain.model.Spacecraft
import com.mosalab.spacecraftisro.core.domain.usecase.SpacecraftUseCase

class DetailSpacecraftViewModel(private val spacecraftUseCase: SpacecraftUseCase) : ViewModel() {
    fun setFavoriteSpacecraft(spacecraft: Spacecraft, newStatus:Boolean) =
        spacecraftUseCase.setFavoriteSpacecraft(spacecraft, newStatus)
}