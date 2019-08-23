package com.paint.fitapp.di.training

import com.paint.fitapp.data.Repository
import com.paint.fitapp.ui.training.TrainingContract
import com.paint.fitapp.ui.training.TrainingPresenter
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

class TrainingModule (internal var trainingView: TrainingContract.View) {
    val compositeDisposable: CompositeDisposable
        @Provides
        get() = CompositeDisposable()

    @Provides
    fun provideView(): TrainingContract.View {
        return trainingView
    }

    @Provides
    fun providePresenter(trainingView: TrainingContract.View, mRrepository: Repository,
                         compositeDisposable: CompositeDisposable
    ): TrainingContract.Presenter {
        return TrainingPresenter(trainingView, mRrepository, compositeDisposable)
    }
}