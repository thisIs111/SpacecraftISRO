// ListSpacecraftResponse.kt
package com.mosalab.spacecraftisro.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSpacecraftResponse(
    @field:SerializedName("spacecrafts")
    val spacecrafts: List<SpacecraftResponse>
)
