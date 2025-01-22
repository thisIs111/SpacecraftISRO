package com.mosalab.spacecraftisro.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mosalab.spacecraftisro.core.data.source.local.entity.SpacecraftEntity

@Database(entities = [SpacecraftEntity::class], version = 1, exportSchema = false)
abstract class SpacecraftDatabase : RoomDatabase(){
    abstract fun  spacecraftDao(): SpacecraftDao
}