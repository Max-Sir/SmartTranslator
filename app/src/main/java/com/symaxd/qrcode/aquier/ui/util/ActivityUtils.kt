package com.symaxd.qrcode.aquier.ui.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.symaxd.qrcode.aquier.R

inline fun exitAlertDialog(context: Context, crossinline onExitAction: () -> Unit) = with(context) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.logout_question))
        .setMessage(getString(R.string.are_you_sure_you_want_to_logout_question))
        .setPositiveButton(getText(R.string.yes)) { dialog, _ ->
            onExitAction.invoke()
            dialog.cancel()
        }
        .setNegativeButton(getText(R.string.no)) { dialog, _ ->
            dialog.cancel()
        }
        .create()
        .show()
}