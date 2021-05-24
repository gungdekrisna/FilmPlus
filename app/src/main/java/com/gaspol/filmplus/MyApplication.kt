package com.gaspol.filmplus

import android.app.Application
import com.gaspol.filmplus.core.di.databaseModule
import com.gaspol.filmplus.core.di.networkModule
import com.gaspol.filmplus.core.di.repositoryModule
import com.gaspol.filmplus.di.useCaseModule
import com.gaspol.filmplus.di.viewModelModule
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