package com.katrenich.alex.appmoney

import android.app.Application

class App : Application() {
    lateinit var instance: App

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}