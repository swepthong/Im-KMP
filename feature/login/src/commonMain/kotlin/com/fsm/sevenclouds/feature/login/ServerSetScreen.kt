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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.fsm.sevenclouds.core.designsystem.component.DEFAULT__BUTTON_SIZE_EXTRA
import com.fsm.sevenclouds.core.designsystem.component.DefaultButton
import com.fsm.sevenclouds.core.designsystem.theme.DefaultTextFieldTheme
import com.fsm.sevenclouds.feature.login.uistate.LoginState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

import sevencloudcrossproject.feature.login.generated.resources.Res
import sevencloudcrossproject.feature.login.generated.resources.confirm
import sevencloudcrossproject.feature.login.generated.resources.ic_back
import sevencloudcrossproject.feature.login.generated.resources.privacy_server_set
import sevencloudcrossproject.feature.login.generated.resources.privacy_server_set_hint
import sevencloudcrossproject.feature.login.generated.resources.server_set


@Composable
fun ServerSetScreen(
    viewModel: LoginViewModel = koinViewModel(), navController: NavController
) {

    val loginState by viewModel.loginState.collectAsState()
    var isAddressError by rememberSaveable { mutableStateOf(false) }
    var server by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        server = viewModel.getServer()
    }
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TopBar(title = stringResource(Res.string.server_set), popUp = {
                navController.navigateUp()
            })

            Spacer(modifier = Modifier.height(65.dp))

            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        stringResource(Res.string.privacy_server_set),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                TextField(
                    isError = isAddressError,
                    value = server,
                    label = { Text(stringResource(Res.string.privacy_server_set_hint)) },
                    onValueChange = {

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

                Spacer(modifier = Modifier.height(28.dp))

                DefaultButton(
                    text = stringResource(Res.string.confirm),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(DEFAULT__BUTTON_SIZE_EXTRA),
                    onClick = {
                        viewModel.saveServer(server)
                        navController.navigateUp()
                    }
                )
            }
        }

}

@Composable
fun TopBar(
    title: String,
    popUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp),
    ) {
        // Back Icon (Placeholder)
        Icon(
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier.size(24.dp).clickable {
                popUp()
            }
        )

        // Title
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}