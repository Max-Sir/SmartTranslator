package com.symaxd.qrcode.aquier.ui.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.ui.fragments.LoginFragment
import com.symaxd.qrcode.aquier.ui.util.exitAlertDialog

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        exitAlertDialog(this, this::finish)
    }
}