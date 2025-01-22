package com.mosalab.spacecraftisro

import android.app.Application
import com.mosalab.spacecraftisro.core.di.databaseModule
import com.mosalab.spacecraftisro.core.di.networkModule
import com.mosalab.spacecraftisro.core.di.repositoryModule
import com.mosalab.spacecraftisro.di.useCaseModule
import com.mosalab.spacecraftisro.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
            startKoin{
                androidLogger()
                androidContext(this@MyApplication)
                modules(
                    listOf(
                        databaseModule,
                        networkModule,
                        repositoryModule,
                        useCaseModule,
                        viewModelModule
                    )
                )
            }
        }
}