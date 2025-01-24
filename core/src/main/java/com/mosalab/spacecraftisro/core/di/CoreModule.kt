package com.mosalab.spacecraftisro.core.di

import androidx.room.Room
import com.mosalab.spacecraftisro.core.data.source.SpacecraftRepository
import com.mosalab.spacecraftisro.core.data.source.local.LocalDataSource
import com.mosalab.spacecraftisro.core.data.source.local.room.SpacecraftDatabase
import com.mosalab.spacecraftisro.core.data.source.remote.RemoteDataSource
import com.mosalab.spacecraftisro.core.data.source.remote.network.ApiService
import com.mosalab.spacecraftisro.core.domain.repository.ISpacecraftRepository
import com.mosalab.spacecraftisro.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SpacecraftDatabase>().spacecraftDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SpacecraftDatabase::class.java, "Spacecraft.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "isro.vercel.app"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/8oTTIaobTfGy0BpRmvIHeXMdSpyRJdC9vuqLAxbodqM=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://isro.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ISpacecraftRepository> {
        SpacecraftRepository(
            get(),
            get(),
            get()
        )
    }
}