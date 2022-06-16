package com.symaxd.qrcode.aquier

import android.graphics.drawable.BitmapDrawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/** Sets QR code image by the processing of the text for ImageView and buttons only!
 * @return Returns Unit and sets view image holder as qr
 */
fun View.setBitmapQrCode(activity: AppCompatActivity, text: String) {
    this.background = BitmapDrawable(
        activity.resources,
        QrCodeCreator.create(text)
    )
}
