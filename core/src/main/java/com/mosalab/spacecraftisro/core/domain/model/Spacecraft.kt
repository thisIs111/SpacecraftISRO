package com.mosalab.spacecraftisro.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Spacecraft(
    val spacecraftId: String,
    val name: String,
    val isFavorite: Boolean
): Parcelable