package com.paint.fitapp.ui.start

import com.paint.fitapp.data.Repository
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList

class StartPresenter(private val view: StartContract.View, private val repository: Repository, private val compositeDisposable: CompositeDisposable) : StartContract.Presenter {
    override fun checkAndRequestPermissions(permissions: Array<String>) {
        // проверяем пермишны
        val listPermissionsNeeded = ArrayList<String>()
        for (permission in permissions) {
            if (!view.checkPermissions(permission)) {
                listPermissionsNeeded.add(permission)
            }
        }

        if (listPermissionsNeeded.isEmpty()) {
            view.onSuccessfulCheckPerm()
        } else {
            view.requestPermissions(listPermissionsNeeded)
        }
    }
}