package br.com.caelum.twittelumapp.application

import android.app.Application

class TwittelumApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private lateinit var instance : TwittelumApplication

        fun getInstance() : TwittelumApplication = instance
    }
}