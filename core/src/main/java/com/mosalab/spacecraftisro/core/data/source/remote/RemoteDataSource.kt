// RemoteDataSource.kt
package com.mosalab.spacecraftisro.core.data.source.remote

import android.util.Log
import com.mosalab.spacecraftisro.core.data.source.remote.network.ApiResponse
import com.mosalab.spacecraftisro.core.data.source.remote.network.ApiService
import com.mosalab.spacecraftisro.core.data.source.remote.response.SpacecraftResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    fun getAllSpacecraft(): Flow<ApiResponse<List<SpacecraftResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.spacecrafts
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                val errorMessage = "Failed to fetch spacecraft data: ${e.message}"
                emit(ApiResponse.Error(errorMessage))
                Log.e("RemoteDataSource", errorMessage, e)
            }
        }.flowOn(Dispatchers.IO)
    }
}
