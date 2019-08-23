package com.paint.fitapp.di.start

import com.paint.fitapp.di.AppScope
import com.paint.fitapp.ui.start.StartActivity
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [StartModule::class])
interface StartComponent {

    fun inject(activity: StartActivity)
}