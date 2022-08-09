package com.symaxd.qrcode.aquier.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.ui.fragments.LoginFragment

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
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.logout_question))
            .setMessage(getString(R.string.are_you_sure_you_want_to_logout_question))
            .setPositiveButton(getText(R.string.yes)) { dialog, _ ->
                finish()
                dialog.cancel()
            }
            .setNegativeButton(getText(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }
}