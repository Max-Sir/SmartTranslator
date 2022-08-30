package com.symaxd.qrcode.aquier.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.ui.fragments.LoginFragment
import com.symaxd.qrcode.aquier.ui.util.exitAlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        exitAlertDialog(this, this::finish)
    }
}