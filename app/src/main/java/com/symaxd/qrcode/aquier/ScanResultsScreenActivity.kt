package com.symaxd.qrcode.aquier

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.symaxd.qrcode.aquier.Constants.Companion.QR_CODE_DECODED_RESULT
import com.symaxd.qrcode.aquier.databinding.ActivityScanResultsScreenBinding
import java.io.File


/**Activity to show scan results*/
class ScanResultsScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultText = intent?.extras?.getString(QR_CODE_DECODED_RESULT) ?: ""
        with(binding.qrResultImageView) {
            setBitmapQrCode(this@ScanResultsScreenActivity, resultText)
            setOnClickListener {
                sharePalette(it.background.toBitmap())
            }
        }
        binding.resultsText.text = resultText
    }

    private fun sharePalette(bitmap: Bitmap) {
        val bitmapUri: Uri = getTmpFileUri(bitmap)
        Log.i("ewg",bitmapUri.toString())
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        startActivity(Intent.createChooser(intent, "Share Image"))
    }

    private fun getTmpFileUri(bitmap:Bitmap): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png").apply {
            createNewFile()
            this.outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            applicationContext,
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }
}