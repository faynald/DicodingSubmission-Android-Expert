package com.farhanrv.iphonecatalogue

import android.app.Application
import com.farhanrv.iphonecatalogue.core.di.databaseModule
import com.farhanrv.iphonecatalogue.core.di.networkModule
import com.farhanrv.iphonecatalogue.core.di.repositoryModule
import com.farhanrv.iphonecatalogue.di.useCaseModule
import com.farhanrv.iphonecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
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