package com.fsm.sevenclouds.feature.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
internal fun QRCodeScanConfirmationScreen(
    scannedData: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 显示扫描到的二维码数据
        Text(
            text = "Scanned Data: $scannedData",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Are you sure you want to proceed?",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // 按钮布局
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // 确认按钮
            Button(
                onClick = { onConfirm() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Confirm")
            }

            // 取消按钮
            Button(
                onClick = { onCancel() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Cancel")
            }
        }
    }
}