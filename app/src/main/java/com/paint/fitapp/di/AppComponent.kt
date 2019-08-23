package com.paint.fitapp.di

import com.paint.fitapp.di.start.StartComponent
import com.paint.fitapp.di.start.StartModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {

    val delayedProcessesComponent: DelayedProcessesComponent

    fun getStartComponent(startModule: StartModule): StartComponent
}