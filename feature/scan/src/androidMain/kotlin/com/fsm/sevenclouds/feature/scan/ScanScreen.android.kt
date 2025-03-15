package com.fsm.sevenclouds.feature.scan

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors


@Composable
actual fun QRCodeScanner(
    onQrCodeScanned: (String) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = Executors.newSingleThreadExecutor()

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera Preview
        AndroidView(factory = { context ->
            val previewView = PreviewView(context)

            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

                val barcodeScanner = BarcodeScanning.getClient()
                val imageAnalyzer = ImageAnalysis.Builder().build().also {
                    it.setAnalyzer(cameraExecutor, { imageProxy ->
                        processImageProxy(barcodeScanner, imageProxy, onQrCodeScanned)
                    })
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, preview, imageAnalyzer
                    )
                } catch (e: Exception) {
                    Log.e("QRCodeScanner", "Camera binding failed", e)
                }
            }, ContextCompat.getMainExecutor(context))

            previewView
        }, modifier = Modifier.fillMaxSize())

        // QR Code Scanning Box
        ScanningBox()
    }
}

@Composable
fun ScanningBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)  // Size of the scanning box
                .border(4.dp, Color.Green)  // Border for the box
                .clip(RoundedCornerShape(12.dp))  // Rounded corners for the box
        )

        // Scanning animation (moving line)
        ScanningLineAnimation(boxHeight = 300.dp)
    }
}

@Composable
fun ScanningLineAnimation(boxHeight: Dp) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scanOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = boxHeight.value,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        drawLine(
            color = Color.Green,
            start = Offset(0f, scanOffset),
            end = Offset(size.width, scanOffset),
            strokeWidth = 4f
        )
    }
}



private var lastScannedCode: String? = null
private var lastScannedTime = 0L
private const val DEBOUNCE_DURATION_MS = 2000 // 2 seconds debounce

@OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    barcodeScanner: BarcodeScanner,
    imageProxy: ImageProxy,
    onQrCodeScanned: (String) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        barcodeScanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    barcode.rawValue?.let { qrCodeValue ->
                        val currentTime = System.currentTimeMillis()
                        if (qrCodeValue != lastScannedCode || currentTime - lastScannedTime > DEBOUNCE_DURATION_MS) {
                            lastScannedCode = qrCodeValue
                            lastScannedTime = currentTime
                            onQrCodeScanned(qrCodeValue)
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("QRCodeScanner", "Barcode scanning failed", e)
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }
}

