package com.mosalab.spacecraftisro.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mosalab.spacecraftisro.core.data.source.local.entity.SpacecraftEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpacecraftDao {
    @Query("SELECT * FROM spacecraft")
    fun getAllSpacecraft(): Flow<List<SpacecraftEntity>>

    @Query("SELECT * FROM spacecraft where isFavorite = 1")
    fun getFavoriteSpacecraft(): Flow<List<SpacecraftEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpacecraft(spacecraft: List<SpacecraftEntity>)

    @Update
    fun updateFavoriteSpacecraft(spacecraft: SpacecraftEntity)
}