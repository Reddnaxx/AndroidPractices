package com.example.urfuandroidpractice.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.urfuandroidpractice.listWithDetails.data.api.AnimeApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideAnimeService(get()) }
}

private fun provideOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient
        .Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl("https://shikimori.one/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideAnimeService(retrofit: Retrofit): AnimeApi {
    return AnimeApi.create(retrofit)
}