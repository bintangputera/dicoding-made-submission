package com.bintangpoetra.moviedbapp

import android.app.Application
import com.bintangpoetra.moviedbapp.core.di.dbModule
import com.bintangpoetra.moviedbapp.core.di.networkModule
import com.bintangpoetra.moviedbapp.core.di.repositoryModule
import com.bintangpoetra.moviedbapp.di.useCaseModule
import com.bintangpoetra.moviedbapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE
import timber.log.Timber

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(NONE)
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    dbModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    networkModule
                )
            )
        }
    }

}