package com.paint.fitapp.ui.training

import com.paint.fitapp.data.Repository
import io.reactivex.disposables.CompositeDisposable

class TrainingPresenter (private val view: TrainingContract.View, private val repository: Repository, private val compositeDisposable: CompositeDisposable) : TrainingContract.Presenter {
}