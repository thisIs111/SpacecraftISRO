// SpacecraftResponse.kt
package com.mosalab.spacecraftisro.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SpacecraftResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String
)
