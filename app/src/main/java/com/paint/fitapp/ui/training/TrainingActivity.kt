package com.paint.fitapp.ui.training

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import javax.inject.Inject

class TrainingActivity : AppCompatActivity(), TrainingContract.View {

    @Inject
    internal var trainingPresenter: TrainingContract.Presenter? = null

    override fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}