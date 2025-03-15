package com.fsm.sevenclouds.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.fsm.sevenclouds.core.designsystem.theme.DefaultButtonTheme
import com.fsm.sevenclouds.core.designsystem.theme.DefaultButtonWithBorderPrimaryTheme

val DEFAULT__BUTTON_SIZE_EXTRA = 60.dp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    enableElevation: Boolean = false,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        modifier = modifier,
        elevation = if (enableElevation) ButtonDefaults.buttonElevation() else ButtonDefaults.buttonElevation(
            0.dp
        ),
        colors = if (enabled) DefaultButtonTheme() else DefaultButtonWithBorderPrimaryTheme(),
        border = BorderStroke(
            0.dp,
            MaterialTheme.colorScheme.primary
        ),
        shape = shape,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}