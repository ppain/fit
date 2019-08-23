package com.paint.fitapp.ui.exercise

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import javax.inject.Inject

class ExerciseActivity  : AppCompatActivity(), ExerciseContract.View {

    @Inject
    internal var exercisePresenter: ExerciseContract.Presenter? = null

    override fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}