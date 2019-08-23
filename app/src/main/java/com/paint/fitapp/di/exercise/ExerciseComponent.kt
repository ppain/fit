package com.paint.fitapp.di.exercise

import com.paint.fitapp.di.AppScope
import com.paint.fitapp.ui.exercise.ExerciseActivity
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [ExerciseModule::class])
interface ExerciseComponent {
    fun inject(activity: ExerciseActivity)
}