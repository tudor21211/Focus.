package com.example.focusparentapp.QRscan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class QrScanner : ComponentActivity() {

    private lateinit var barCodeLauncher: ActivityResultLauncher<ScanOptions>

    private var textResult = mutableStateOf("")

    private val requestCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showCamera()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }

     private fun checkCameraPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Camera permission needed to scan QR code", Toast.LENGTH_LONG)
                .show()
            setResult(404)
            finish()
        } else {
            requestCameraPermission.launch(android.Manifest.permission.CAMERA)
        }

    }

    private fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR Code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)
        barCodeLauncher.launch(options)
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barCodeLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                textResult.value = result.contents
                println("TEXT RESULT IS ${textResult.value}")
                //TODO Important send the result to the server
                setResult(100, Intent().putExtra("result", textResult.value))
                finish() //ending the activity and resuming the main activity
            } else {
                Toast.makeText(this, "No result", Toast.LENGTH_LONG).show()
                setResult(404)
                finish()

            }
        }
        checkCameraPermission(this@QrScanner)
    }
}


