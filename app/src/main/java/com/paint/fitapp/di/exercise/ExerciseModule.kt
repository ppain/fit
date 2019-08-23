package com.paint.fitapp.di.exercise

import com.paint.fitapp.data.Repository
import com.paint.fitapp.ui.exercise.ExerciseContract
import com.paint.fitapp.ui.exercise.ExercisePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ExerciseModule (internal var exerciseView: ExerciseContract.View) {
    val compositeDisposable: CompositeDisposable
        @Provides
        get() = CompositeDisposable()

    @Provides
    fun provideView(): ExerciseContract.View {
        return exerciseView
    }

    @Provides
    fun providePresenter(exerciseView: ExerciseContract.View, mRrepository: Repository,
                         compositeDisposable: CompositeDisposable
    ): ExerciseContract.Presenter {
        return ExercisePresenter(exerciseView, mRrepository, compositeDisposable)
    }
}