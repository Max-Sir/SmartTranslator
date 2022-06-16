package com.symaxd.qrcode.aquier

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.symaxd.qrcode.aquier.databinding.ActivityMainBinding

/** Home Activity*/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**Configuring and inflating the menu resource to the view */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**Processing of selected menu items*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.scan_qr_code -> {
                startActivity(Intent(this, ScannerActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /** Initializes the ui */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generate.setOnClickListener {
            val text = binding.editText.text.toString()
            when (text.isEmpty()) {
                false -> it.setBitmapQrCode(this@MainActivity, text)
                else -> {
                    Toast.makeText(this, "text was empty or null '$text'", Toast.LENGTH_LONG).show()
                    this.finish()
                }
            }
        }
        binding.generate.performClick()
    }
}