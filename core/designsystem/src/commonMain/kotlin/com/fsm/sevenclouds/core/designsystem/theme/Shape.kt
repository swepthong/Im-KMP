package com.fsm.sevenclouds.core.designsystem.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)


@Composable
fun DefaultImageButtonTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.primary,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)

@Composable
fun DefaultButtonWithBorderPrimaryTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)

@Composable
fun DefaultButtonTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.secondary
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextFieldTheme() = textFieldColors(
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    cursorColor = MaterialTheme.colorScheme.onBackground,
    focusedIndicatorColor = TextFieldColor,
    unfocusedIndicatorColor = TextFieldColor,
    disabledContainerColor = TextFieldColor,
    disabledTextColor = MaterialTheme.colorScheme.onBackground,
    disabledIndicatorColor = Color.Transparent,
)


@Composable
fun DefaultCheckBoxTheme() = CheckboxDefaults.colors(
    checkedColor = MaterialTheme.colorScheme.primary,
    uncheckedColor = MaterialTheme.colorScheme.primary,
    checkmarkColor = MaterialTheme.colorScheme.background
)


@Composable
fun DefaultCardColorsTheme() = CardColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.onBackground,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.onBackground,
)


@Composable
fun DefaultNavigationBarItemTheme() = NavigationBarItemDefaults.colors(
    selectedIconColor = MaterialTheme.colorScheme.secondary,
    unselectedIconColor = MaterialTheme.colorScheme.primary,
    unselectedTextColor = MaterialTheme.colorScheme.primary.copy(.7f),
    selectedTextColor = MaterialTheme.colorScheme.secondary,
    indicatorColor = MaterialTheme.colorScheme.background,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBarTheme() = TopAppBarDefaults.topAppBarColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    titleContentColor = MaterialTheme.colorScheme.onSecondary,
    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
)


@Composable
fun TextFieldWithTransparentTheme() = TextFieldDefaults.colors(
    // cursorColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent
)