package com.paint.fitapp.ui.exercise

import com.paint.fitapp.data.Repository
import io.reactivex.disposables.CompositeDisposable

class ExercisePresenter (private val view: ExerciseContract.View, private val repository: Repository, private val compositeDisposable: CompositeDisposable) : ExerciseContract.Presenter {
}