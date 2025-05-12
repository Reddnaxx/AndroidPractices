package com.example.urfuandroidpractice

import android.app.Application
import com.example.local.di.localModule
import com.example.network.di.networkModule
import com.example.urfuandroidpractice.di.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                rootModule,
                networkModule,
                localModule
            )
        }
    }
}