package com.mosalab.spacecraftisro.core.data.source.local

import com.mosalab.spacecraftisro.core.data.source.local.entity.SpacecraftEntity
import com.mosalab.spacecraftisro.core.data.source.local.room.SpacecraftDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val spacecraftDao: SpacecraftDao) {
    fun getAllSpacecraft(): Flow<List<SpacecraftEntity>> = spacecraftDao.getAllSpacecraft()


    fun getFavoriteSpacecraft(): Flow<List<SpacecraftEntity>> = spacecraftDao.getFavoriteSpacecraft()

    suspend fun insertSpacecraft(spacecraftList: List<SpacecraftEntity>) = spacecraftDao.insertSpacecraft(spacecraftList)


    fun setFavoriteSpacecraft(spacecraft: SpacecraftEntity, newState: Boolean){
        spacecraft.isFavorite = newState
        spacecraftDao.updateFavoriteSpacecraft(spacecraft)
    }
}