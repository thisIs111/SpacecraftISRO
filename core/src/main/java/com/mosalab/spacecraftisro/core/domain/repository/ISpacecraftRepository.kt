package com.mosalab.spacecraftisro.core.domain.repository

import com.mosalab.spacecraftisro.core.data.Resource
import com.mosalab.spacecraftisro.core.domain.model.Spacecraft
import kotlinx.coroutines.flow.Flow


interface ISpacecraftRepository {
    fun getAllSpacecraft(): Flow<Resource<List<Spacecraft>>>

    fun getFavoriteSpacecraft(): Flow<List<Spacecraft>>

    fun setFavoriteSpacecraft(spacecraft: Spacecraft, state: Boolean)
}