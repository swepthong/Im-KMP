package com.fsm.sevenclouds.feature.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fsm.sevenclouds.core.common.HomeRoute
import com.fsm.sevenclouds.core.common.LoginRoute
import com.fsm.sevenclouds.core.common.ServerSetRoute
import com.fsm.sevenclouds.core.designsystem.component.DEFAULT__BUTTON_SIZE_EXTRA
import com.fsm.sevenclouds.core.designsystem.component.DefaultButton
import com.fsm.sevenclouds.core.designsystem.component.PasswordTextField
import com.fsm.sevenclouds.core.designsystem.theme.DefaultTextFieldTheme
import com.fsm.sevenclouds.feature.login.uistate.LoginState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import sevencloudcrossproject.feature.login.generated.resources.Res
import sevencloudcrossproject.feature.login.generated.resources.account
import sevencloudcrossproject.feature.login.generated.resources.account_login
import sevencloudcrossproject.feature.login.generated.resources.login
import sevencloudcrossproject.feature.login.generated.resources.password
import sevencloudcrossproject.feature.login.generated.resources.server_set

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel(), navController: NavController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    val loginState by viewModel.loginState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.checkLogin()
        username = viewModel.getUserName()
        password = viewModel.getPassword()
    }

    when (loginState) {
        is LoginState.Loading -> {
           // CircularProgressIndicator()
        }
        is LoginState.Error -> {}
        is LoginState.Idle -> {}
        is LoginState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(HomeRoute) {
                    popUpTo(LoginRoute) { inclusive = true }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            stringResource(Res.string.server_set),
            modifier = Modifier.align(Alignment.TopEnd)
                .clickable {
                    navController.navigate(ServerSetRoute)
                },
            style = MaterialTheme.typography.bodyMedium
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(Res.string.account_login),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(30.dp)) // Spacing between text fields
            // Account Text Field
            TextField(
                isError = isUsernameError,
                value = username,
                label = { Text(text = stringResource(Res.string.account)) } ,
                onValueChange = {
                    username = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )
            Spacer(modifier = Modifier.height(16.dp)) // Spacing between text fields

            PasswordTextField(
                // isError = isPasswordError,
                value = password,
                label = { Text(stringResource(Res.string.password)) },
                onValueChange = {
                    password = it
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(32.dp))

            DefaultButton(
                text = stringResource(Res.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DEFAULT__BUTTON_SIZE_EXTRA),
                onClick = {
                    viewModel.performLogin(username, password)
                }
            )
        }
    }
}
