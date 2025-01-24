package com.mosalab.spacecraftisro.core.domain.usecase

import com.mosalab.spacecraftisro.core.domain.model.Spacecraft
import com.mosalab.spacecraftisro.core.domain.repository.ISpacecraftRepository

class SpacecraftInteractor(private val spacecraftRepository: ISpacecraftRepository): SpacecraftUseCase{


    override fun getAllSpacecraft() = spacecraftRepository.getAllSpacecraft()

    override fun getFavoriteSpacecraft() = spacecraftRepository.getFavoriteSpacecraft()

    override fun setFavoriteSpacecraft(spacecraft: Spacecraft, state: Boolean) = spacecraftRepository.setFavoriteSpacecraft(spacecraft, state)
}