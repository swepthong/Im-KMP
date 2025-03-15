package com.fsm.sevenclouds.feature.scan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVCaptureConnection
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVCaptureDeviceInput
import platform.AVFoundation.AVCaptureMetadataOutput
import platform.AVFoundation.AVCaptureMetadataOutputObjectsDelegateProtocol
import platform.AVFoundation.AVCaptureOutput
import platform.AVFoundation.AVCaptureSession
import platform.AVFoundation.AVCaptureSessionPresetPhoto
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.AVCaptureVideoPreviewLayer
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.AVFoundation.AVMetadataMachineReadableCodeObject
import platform.AVFoundation.AVMetadataObjectTypeQRCode
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIView
import platform.darwin.NSObject
import platform.darwin.dispatch_get_main_queue

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun QRCodeScanner(onQrCodeScanned: (String) -> Unit) {
    UIKitView(
        factory = {
            // 创建相机预览视图
            val cameraView = UIView(frame = CGRectMake(0.0, 0.0, 300.0, 400.0)) // 修改大小以适应需要的布局

            // 配置AVCaptureSession用于相机输入和二维码检测
            val captureSession = AVCaptureSession().apply {
                sessionPreset = AVCaptureSessionPresetPhoto
            }

            // 获取视频输入设备 (后置摄像头)
            val videoDevice = AVCaptureDevice.defaultDeviceWithMediaType(AVMediaTypeVideo)!!
            val videoInput = AVCaptureDeviceInput.deviceInputWithDevice(videoDevice, null)!!
            if (captureSession.canAddInput(videoInput)) {
                captureSession.addInput(videoInput)
            }

            // 配置输出元数据 (用于识别二维码)
            val metadataOutput = AVCaptureMetadataOutput()
            if (captureSession.canAddOutput(metadataOutput)) {
                captureSession.addOutput(metadataOutput)
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
                                captureSession.stopRunning()
                            }
                        }
                    }
                }, dispatch_get_main_queue())
                metadataOutput.metadataObjectTypes = listOf(AVMetadataObjectTypeQRCode)
            }

            // 创建预览层，并将其添加到cameraView中
            val previewLayer = AVCaptureVideoPreviewLayer(session = captureSession).apply {
                videoGravity = AVLayerVideoGravityResizeAspectFill
                frame = cameraView.bounds
            }
            cameraView.layer.addSublayer(previewLayer)

            // 启动会话
            captureSession.startRunning()

            // 返回用于相机预览的UIView
            cameraView
        },
        // 适配Compose Modifier
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    )
}