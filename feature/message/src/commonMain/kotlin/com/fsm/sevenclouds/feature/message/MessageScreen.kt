package com.fsm.sevenclouds.feature.message

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.ChatRoute
import com.fsm.sevenclouds.core.common.ScanRoute
import com.fsm.sevenclouds.core.data.model.ChatDetail
import com.fsm.sevenclouds.core.designsystem.theme.DefaultTopAppBarTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import sevencloudcrossproject.feature.message.generated.resources.Res
import sevencloudcrossproject.feature.message.generated.resources.message
import sevencloudcrossproject.feature.message.generated.resources.scan
import sevencloudcrossproject.feature.message.generated.resources.send_group_chat


@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun MessageScreen(
    navController: NavController,
    viewModel: MessageViewModel = koinViewModel()
) {

    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.message)) },
                colors = DefaultTopAppBarTheme(),
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onSecondary)
                    }
                },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        modifier = Modifier.padding(end = 20.dp),
                        offset = DpOffset((-20).dp, (-20).dp),
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(Res.string.send_group_chat)) },
                            onClick = { }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(Res.string.scan)) },
                            onClick = {
                                coroutineScope.launch {
                                    if (viewModel.checkCameraPermission()) {
                                        navController.navigate(ScanRoute)
                                    }
                                }
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val chats by viewModel.chats.collectAsStateWithLifecycle()
        ChatList(chats,
            contentPadding = innerPadding,
            onChatClicked = {
                navController.navigate(ChatRoute(it))
        })
    }
}



@Composable
internal fun ChatList(
    chats: List<ChatDetail>,
    contentPadding: PaddingValues,
    onChatClicked: (chatId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val log = Logger.withTag("MessageScreen")
    log.d(" ChatList  chats: ${chats.toMutableList()}  ")
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {

        items(chats,
            key = {
                it.id
            }) { chat ->
            ChatRow(
                chat = chat,
                onClick = { onChatClicked(chat.lastMessageId) },
            )
        }
    }
}


