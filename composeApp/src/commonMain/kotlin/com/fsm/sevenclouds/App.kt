package com.fsm.sevenclouds

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fsm.sevenclouds.core.common.ChatRoute
import com.fsm.sevenclouds.core.common.ContactsRoute
import com.fsm.sevenclouds.core.common.DocumentRoute
import com.fsm.sevenclouds.core.common.HomeRoute
import com.fsm.sevenclouds.core.common.LoginRoute
import com.fsm.sevenclouds.core.common.MessageRoute
import com.fsm.sevenclouds.core.common.ScanRoute
import com.fsm.sevenclouds.core.common.ServerSetRoute
import com.fsm.sevenclouds.core.common.WorkSpaceRoute
import com.fsm.sevenclouds.core.designsystem.theme.SevenCloudsTheme
import com.fsm.sevenclouds.feature.home.HomeScreen
import com.fsm.sevenclouds.feature.login.LoginScreen
import com.fsm.sevenclouds.feature.login.ServerSetScreen
import com.fsm.sevenclouds.feature.message.ChatScreen
import com.fsm.sevenclouds.feature.message.MessageScreen
import com.fsm.sevenclouds.feature.scan.ScanScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SevenCloudsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val navController = rememberNavController()
            NavHost(
                navController, startDestination = LoginRoute,
                popEnterTransition = {
                    scaleIn(initialScale = 1.1F) + fadeIn()
                },
                popExitTransition = {
                    scaleOut(targetScale = 0.9F) + fadeOut()
                },
            ) {
                composable<LoginRoute> {
                    LoginScreen(navController = navController)
                }
                composable<ServerSetRoute> {
                    ServerSetScreen(navController = navController)
                }
                composable<HomeRoute> {
                    HomeScreen(navController = navController, homeNav = {
                        HomeNavHost(navController, it)
                    })
                }
                composable<ScanRoute> {
                    ScanScreen(navController = navController)
                }
                composable<ChatRoute> {
                    ChatScreen(navController = navController)
                }
            }
        }
    }

}

@Composable
private fun HomeNavHost(navController: NavHostController, navBottomBarController: NavHostController) {
    NavHost(
        startDestination = MessageRoute,
        navController = navBottomBarController,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<MessageRoute> {
            MessageScreen(navController = navController)
        }
        composable<DocumentRoute> {
            Text("Document")
        }
        composable<WorkSpaceRoute> {
            Text("WorkSpace")
        }
        composable<ContactsRoute> {
            Text("Contacts")
        }
    }
}