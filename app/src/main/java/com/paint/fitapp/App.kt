package com.paint.fitapp

import android.app.Application
import android.content.Context
import com.paint.fitapp.di.AppComponent

class App : Application() {

    private var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        app = this
    }

    companion object {

        var app: App? = null
            private set

        val ctx: Context
            get() = app!!.applicationContext

        fun getComponent(): AppComponent? {
            return app!!.component
        }
    }
}