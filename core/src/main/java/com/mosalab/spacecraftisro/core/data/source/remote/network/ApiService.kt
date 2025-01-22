package com.mosalab.spacecraftisro.core.data.source.remote.network

import com.mosalab.spacecraftisro.core.data.source.remote.response.ListSpacecraftResponse
import retrofit2.http.GET

interface ApiService {
    @GET("spacecrafts")
    suspend fun getList(): ListSpacecraftResponse
}