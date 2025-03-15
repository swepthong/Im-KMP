package com.fsm.sevenclouds.feature.scan
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVCaptureConnection
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVCaptureDeviceInput
import platform.AVFoundation.AVCaptureMetadataOutput
import platform.AVFoundation.AVCaptureMetadataOutputObjectsDelegateProtocol
import platform.AVFoundation.AVCaptureOutput
import platform.AVFoundation.AVCaptureSession
import platform.AVFoundation.AVCaptureVideoPreviewLayer
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.AVMetadataMachineReadableCodeObject
import platform.AVFoundation.AVMetadataObjectTypeQRCode
import platform.UIKit.UIApplication
import platform.UIKit.UIScreen
import platform.darwin.NSObject
import platform.darwin.dispatch_get_main_queue
import kotlin.OptIn
import kotlin.String
import kotlin.Unit
import kotlin.apply
import kotlin.let

/*
class QRCodeScanner () {
    private var captureSession: AVCaptureSession? = null
    private var videoPreviewLayer: AVCaptureVideoPreviewLayer? = null

    // Start scanning QR codes
    @OptIn(ExperimentalForeignApi::class)
    fun startScanning(onQrCodeScanned: (String) -> Unit) {
        captureSession = AVCaptureSession()

        // Get the back-facing camera
        val videoCaptureDevice = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)
        val videoInput = videoCaptureDevice?.let { AVCaptureDeviceInput(device = it, error = null) } ?: return

        // Ensure the camera input is available
        if (captureSession?.canAddInput(videoInput) == true) {
            captureSession?.addInput(videoInput)
        } else {
            return
        }

        // Set up metadata output for QR code detection
        val metadataOutput = AVCaptureMetadataOutput()
        if (captureSession?.canAddOutput(metadataOutput) == true) {
            captureSession?.addOutput(metadataOutput)

            metadataOutput.setMetadataObjectsDelegate(object : NSObject(), AVCaptureMetadataOutputObjectsDelegateProtocol {
                override fun captureOutput(
                    output: AVCaptureOutput,
                    didOutputMetadataObjects: List<*>,
                    fromConnection: AVCaptureConnection
                ) {
                    didOutputMetadataObjects.forEach { obj ->
                        val metadataObject = obj as? AVMetadataMachineReadableCodeObject
                        metadataObject?.stringValue?.let { qrCodeValue ->
                            onQrCodeScanned(qrCodeValue)
                            stopScanning() // Stop scanning after first match
                        }
                    }
                }
            }, dispatch_get_main_queue())

            metadataOutput.metadataObjectTypes = listOf(AVMetadataObjectTypeQRCode)
        }

        // Set up preview layer
        videoPreviewLayer = AVCaptureVideoPreviewLayer(session = captureSession!!).apply {
            videoGravity = AVLayerVideoGravityResizeAspectFill
            frame = UIScreen.mainScreen.bounds
            (UIApplication.sharedApplication.keyWindow?.rootViewController?.view)?.layer?.addSublayer(this)
        }

        // Start capturing
        captureSession?.startRunning()
    }

    // Stop scanning QR codes
    fun stopScanning() {
        captureSession?.stopRunning()
        captureSession = null
        videoPreviewLayer?.removeFromSuperlayer()
        videoPreviewLayer = null
    }
}
*/
