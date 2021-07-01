package com.example.nextbaseapp

import android.app.Application
import android.content.Context
import com.example.nextbaseapp.di.AppComponent
import com.example.nextbaseapp.di.DaggerAppComponent

class MyNextBaseApp : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

    fun Context.asApp() = this.applicationContext as MyNextBaseApp

}