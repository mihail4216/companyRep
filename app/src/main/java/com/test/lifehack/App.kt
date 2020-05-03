package com.test.lifehack

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.lifehack.logic.network.ApiManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        val instance: App by lazy { Holder.INSTANCE }
        const val PHOTO_HOST = "http://megakohz.bget.ru/test_task"
    }

    object Holder {
        lateinit var INSTANCE: App
    }

    lateinit var API: ApiManager


    override fun onCreate() {
        super.onCreate()
        Holder.INSTANCE = this
        initNetwork()
    }


    private fun initNetwork() {
//        инизиализация логера
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        //инициализация клиента для запроса на сервер
        val static = Retrofit.Builder()
            .baseUrl("http://megakohz.bget.ru")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
//
        API = static.create(ApiManager::class.java)
    }

}