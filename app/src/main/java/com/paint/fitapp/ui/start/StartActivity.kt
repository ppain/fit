package com.paint.fitapp.ui.start

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.paint.fitapp.App
import com.paint.fitapp.R
import com.paint.fitapp.di.start.StartModule
import com.paint.fitapp.utils.Constants
import javax.inject.Inject

class StartActivity : AppCompatActivity(), StartContract.View {

    private val listPermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    @Inject
    internal var startPresenter: StartContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        App.getComponent()?.getStartComponent(StartModule(this as StartContract.View))?.inject(this)

        init()
    }

    private fun init() {

        startPresenter!!.checkAndRequestPermissions(listPermission)
    }

    override fun requestPermissions(permissions: List<*>) {
        ActivityCompat.requestPermissions(this, permissions.toTypedArray() as Array<String>,
            Constants.PERMISSIONS_REQUEST_CODE)
    }

    override fun checkPermissions(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onSuccessfulCheckPerm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        // если пользователь закрыл запрос на разрешение, не дав ответа, массив grantResults будет пустым
        if (requestCode == Constants.PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                onSuccessfulCheckPerm()
            } else {
                showMessage("Необходимо в настройках приложения разрешить доступ к камере и хранилищу")
                //Exit
                val handler = Handler()
                handler.postDelayed({
                    finish()
                    System.exit(0)
                }, 3000)
            }
        }
    }

}