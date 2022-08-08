package com.symaxd.qrcode.aquier.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.symaxd.qrcode.aquier.Constants.Companion.QR_CODE_DECODED_RESULT
import com.symaxd.qrcode.aquier.databinding.ActivityScanResultsScreenBinding
import com.symaxd.qrcode.aquier.setBitmapQrCode

/**Activity to show scan results*/
class ScanResultsScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanResultsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultText = intent?.extras?.getString(QR_CODE_DECODED_RESULT) ?: ""
        binding.qrResultImageView.setBitmapQrCode(this, resultText)
        binding.resultsText.text = resultText
    }
}