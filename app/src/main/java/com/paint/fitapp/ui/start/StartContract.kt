package com.paint.fitapp.ui.start

interface StartContract {
    interface View {
        fun checkPermissions(permission: String): Boolean
        fun requestPermissions(permissions: List<*>)
        fun onSuccessfulCheckPerm()
        fun showMessage(text: String)
    }

    interface Presenter {
        fun checkAndRequestPermissions(permissions: Array<String>)
    }
}