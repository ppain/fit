package com.paint.fitapp.di.start

import com.paint.fitapp.data.Repository
import com.paint.fitapp.ui.start.StartContract
import com.paint.fitapp.ui.start.StartPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class StartModule(internal var startView: StartContract.View) {

    val compositeDisposable: CompositeDisposable
        @Provides
        get() = CompositeDisposable()

    @Provides
    fun provideView(): StartContract.View {
        return startView
    }

    @Provides
    fun providePresenter(startView: StartContract.View, mRrepository: Repository,
                         compositeDisposable: CompositeDisposable
    ): StartContract.Presenter {
        return StartPresenter(startView, mRrepository, compositeDisposable)
    }
}