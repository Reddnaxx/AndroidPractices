package com.example.urfuandroidpractice

import android.app.Application
import com.example.urfuandroidpractice.modules.networkModule
import com.example.urfuandroidpractice.modules.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(rootModule, networkModule))
        }
    }
}