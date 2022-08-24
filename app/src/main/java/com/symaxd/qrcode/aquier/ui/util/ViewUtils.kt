package com.symaxd.qrcode.aquier

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.symaxd.qrcode.aquier.core.QrCodeCreator

/** Sets QR code image by the processing of the text for ImageView and buttons only!
 * @return Returns Unit and sets view image holder as qr
 */
fun View.setBitmapQrCode(context: Context, text: String) {
    this.background = BitmapDrawable(
        context.resources,
        QrCodeCreator.create(text)
    )
}
