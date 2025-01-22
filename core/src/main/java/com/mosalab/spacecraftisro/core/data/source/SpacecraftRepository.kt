package com.mosalab.spacecraftisro.core.data.source

import com.mosalab.spacecraftisro.core.data.NetworkBoundResource
import com.mosalab.spacecraftisro.core.data.Resource
import com.mosalab.spacecraftisro.core.data.source.local.LocalDataSource
import com.mosalab.spacecraftisro.core.data.source.remote.RemoteDataSource
import com.mosalab.spacecraftisro.core.data.source.remote.network.ApiResponse
import com.mosalab.spacecraftisro.core.data.source.remote.response.SpacecraftResponse
import com.mosalab.spacecraftisro.core.domain.model.Spacecraft
import com.mosalab.spacecraftisro.core.domain.repository.ISpacecraftRepository
import com.mosalab.spacecraftisro.core.utils.AppExecutors
import com.mosalab.spacecraftisro.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpacecraftRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISpacecraftRepository {
    override fun getAllSpacecraft(): Flow<Resource<List<Spacecraft>>> =
        object : NetworkBoundResource<List<Spacecraft>, List<SpacecraftResponse>>() {
            override fun loadFromDB(): Flow<List<Spacecraft>> {
                return localDataSource.getAllSpacecraft().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Spacecraft>?): Boolean =
                data.isNullOrEmpty() // mengambil data dari internet hanya jika data di database kosong
//                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<SpacecraftResponse>>> =
                remoteDataSource.getAllSpacecraft()

            override suspend fun saveCallResult(data: List<SpacecraftResponse>) {
                val spacecraftList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSpacecraft(spacecraftList)
            }
        }.asFlow()


    override fun getFavoriteSpacecraft(): Flow<List<Spacecraft>> {
        return localDataSource.getFavoriteSpacecraft().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteSpacecraft(spacecraft: Spacecraft, state: Boolean) {
        val spacecraftEntity = DataMapper.mapDomainToEntity(spacecraft)
        appExecutors.diskIO().execute { localDataSource.setFavoriteSpacecraft(spacecraftEntity, state) }
    }


}