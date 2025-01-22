package com.mosalab.spacecraftisro.core.utils

import com.mosalab.spacecraftisro.core.data.source.local.entity.SpacecraftEntity
import com.mosalab.spacecraftisro.core.data.source.remote.response.SpacecraftResponse
import com.mosalab.spacecraftisro.core.domain.model.Spacecraft

object DataMapper {
    fun mapResponsesToEntities(input: List<SpacecraftResponse>): List<SpacecraftEntity>{
        val spacecraftList = ArrayList<SpacecraftEntity>()
        input.map { 
            val spacecraft = SpacecraftEntity(
                spacecraftId = it.id,
                name = it.name,
                isFavorite = false
            )
            spacecraftList.add(spacecraft)
        }
        return spacecraftList
    }

    fun mapEntitiesToDomain(input : List<SpacecraftEntity>): List<Spacecraft> =
            input.map {
                Spacecraft(
                    spacecraftId = it.spacecraftId,
                    name = it.name,
                    isFavorite = it.isFavorite
                )
            }
    fun mapDomainToEntity(input: Spacecraft) = SpacecraftEntity(
        spacecraftId = input.spacecraftId,
        name = input.name,
        isFavorite = input.isFavorite
    )
}