package com.paint.fitapp.di.training

import com.paint.fitapp.di.AppScope
import com.paint.fitapp.ui.training.TrainingActivity
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [TrainingModule::class])
interface TrainingComponent {
    fun inject(activity: TrainingActivity)
}