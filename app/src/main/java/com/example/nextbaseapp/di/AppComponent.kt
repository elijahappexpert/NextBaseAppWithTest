package com.example.nextbaseapp.di

import android.content.Context
import com.example.nextbaseapp.ChartView.MainActivity
import com.example.nextbaseapp.MapView.MapViewFragment
import com.example.nextbaseapp.MyNextBaseApp
import com.example.nextbaseapp.repo.Repository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component
interface AppComponent  {

    //this inject method is what defines the "container flows" or multiple activity flows
    fun injectMainActivity(activity: MainActivity)

    fun injectMapViewFragment(fragment:MapViewFragment)

    //this is where we'll have the instances of all our dependencies
    fun repository(): Repository


}