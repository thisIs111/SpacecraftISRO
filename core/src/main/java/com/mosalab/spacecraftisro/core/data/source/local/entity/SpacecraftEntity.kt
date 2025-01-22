package com.mosalab.spacecraftisro.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spacecraft")
data class SpacecraftEntity(
    @PrimaryKey
    val spacecraftId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)