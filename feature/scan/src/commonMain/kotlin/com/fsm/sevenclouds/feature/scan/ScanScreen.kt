package com.fsm.sevenclouds.feature.scan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.feature.scan.uistate.ScanState
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
expect fun QRCodeScanner(
    onQrCodeScanned: (String) -> Unit
)


@OptIn(KoinExperimentalAPI::class)
@Composable
fun ScanScreen(navController: NavHostController,  viewModel: ScanViewModel = koinViewModel()) {
    val scanState by viewModel.scanState.collectAsState()
    when (scanState) {
        is ScanState.Idle,
        is ScanState.Error -> {
            QRCodeScanner(
                onQrCodeScanned = { result ->
                    Logger.d("scannedCode: $result")
                    viewModel.submitCodeInfo(result)
                }
            )

            // Text(text = "Scanned Code: $scannedCode")
        }

        is ScanState.Confirm -> {
            val scannedCode = (scanState as ScanState.Confirm).code
            QRCodeScanConfirmationScreen(
                scannedData = scannedCode,
                onConfirm = {
                    // 处理确认逻辑，例如提交数据或进行下一步
                    println("Confirmed! Proceeding with: $scannedCode")
                    viewModel.codeConfirm(scannedCode)
                },
                onCancel = {
                    // 处理取消逻辑，例如返回到主界面或重新扫描
                    println("Cancelled. Returning to previous step.")
                    navController.navigateUp()
                }
            )
        }

        is ScanState.Success -> {
            navController.navigateUp()
        }
    }
}


