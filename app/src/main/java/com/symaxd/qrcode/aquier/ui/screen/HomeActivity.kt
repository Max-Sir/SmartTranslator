package com.symaxd.qrcode.aquier.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.biometrics.BiometricPromptUtils
import com.symaxd.qrcode.aquier.biometrics.BiometricPromptUtils.CIPHERTEXT_WRAPPER
import com.symaxd.qrcode.aquier.biometrics.BiometricPromptUtils.SHARED_PREFS_FILENAME
import com.symaxd.qrcode.aquier.biometrics.CiphertextWrapper
import com.symaxd.qrcode.aquier.biometrics.CryptographyManagerImpl
import com.symaxd.qrcode.aquier.databinding.ActivityHomeBinding
import com.symaxd.qrcode.aquier.setBitmapQrCode
import com.symaxd.qrcode.aquier.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/** Home Activity*/
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var viewModel: HomeViewModel
    private val cryptographyManager by lazyOf(CryptographyManagerImpl())
    private var ciphertextWrapper: CiphertextWrapper? = null

    /**Configuring and inflating the menu resource to the view */

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
        showBiometricPromptForEncryption()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generate.setOnClickListener {
            val text = binding.editText.text.toString()
            when (text.isEmpty()) {
                false -> it.setBitmapQrCode(this@HomeActivity, text)
                else -> {
                    Toast.makeText(this, "text was empty or null '$text'", Toast.LENGTH_LONG).show()
                    this.finish()
                }
            }
        }
        binding.generate.performClick()
    }

    @Suppress("deprecation")
    private fun showBiometricPromptForEncryption() {
        val canAuthenticate = BiometricManager.from(applicationContext).canAuthenticate()
        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            val secretKeyName = "biometric_sample_encryption_key"
            val cipher = cryptographyManager.getInitializedCipherForEncryption(secretKeyName)
            val biometricPrompt =
                BiometricPromptUtils.createBiometricPrompt(
                    this,
                    ::encryptAndStoreUsername,
                    this::finish
                )
            val promptInfo = BiometricPromptUtils.createPromptInfo(this)
            biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        }
    }

    @Suppress("deprecation")
    private fun showBiometricPromptForDecryption() {
        val canAuthenticate = BiometricManager.from(applicationContext).canAuthenticate()
        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            ciphertextWrapper?.let { textWrapper ->
                val secretKeyName = "biometric_sample_encryption_key"
                Timber.i("hellom ${textWrapper.ciphertext.toString()}")
                val cipher = cryptographyManager.getInitializedCipherForDecryption(
                    secretKeyName, textWrapper.initializationVector
                )
                val biometricPrompt =
                    BiometricPromptUtils.createBiometricPrompt(
                        this,
                        ::decryptServerTokenFromStorage
                    )
                val promptInfo = BiometricPromptUtils.createPromptInfo(this)
                biometricPrompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
            }
        }
    }


    private fun encryptAndStoreUsername(authResult: BiometricPrompt.AuthenticationResult) {
//        loginViewModel.startLoadingEvent()

        authResult.cryptoObject?.cipher?.apply {
//            val text = binding.username.text.toString()
            Timber.plant(Timber.DebugTree())
//            TimberImpl.d("text before: $text")
            val encryptedServerTokenWrapper =
                cryptographyManager.encryptData("", this)
            Timber.d("ciphertext is now is: ${encryptedServerTokenWrapper.ciphertext}")
            cryptographyManager.persistCiphertextWrapperToSharedPrefs(
                encryptedServerTokenWrapper,
                applicationContext,
                SHARED_PREFS_FILENAME,
                Context.MODE_PRIVATE,
                CIPHERTEXT_WRAPPER
            )
        }
        startActivity(Intent(this, ScannerActivity::class.java))
        finish()
    }

    private fun decryptServerTokenFromStorage(authResult: BiometricPrompt.AuthenticationResult) {
        ciphertextWrapper?.let { textWrapper ->
            authResult.cryptoObject?.cipher?.let {
                val plaintextUsername =
                    cryptographyManager.decryptData(textWrapper.ciphertext, it)
                Timber.i("plaintext: $plaintextUsername")

                startActivity(Intent(this, ScannerActivity::class.java))
            }
        }
    }
}