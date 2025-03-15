package com.fsm.sevenclouds.feature.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.common.ChangeStatusBarColors
import com.fsm.sevenclouds.core.common.ContactsRoute
import com.fsm.sevenclouds.core.common.DocumentRoute
import com.fsm.sevenclouds.core.common.MessageRoute
import com.fsm.sevenclouds.core.common.WorkSpaceRoute
import com.fsm.sevenclouds.core.designsystem.theme.DefaultCardColorsTheme
import com.fsm.sevenclouds.core.designsystem.theme.DefaultNavigationBarItemTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import sevencloudcrossproject.feature.home.generated.resources.Res
import sevencloudcrossproject.feature.home.generated.resources.contact
import sevencloudcrossproject.feature.home.generated.resources.document
import sevencloudcrossproject.feature.home.generated.resources.ic_contact
import sevencloudcrossproject.feature.home.generated.resources.ic_document
import sevencloudcrossproject.feature.home.generated.resources.ic_message
import sevencloudcrossproject.feature.home.generated.resources.ic_workspace
import sevencloudcrossproject.feature.home.generated.resources.message
import sevencloudcrossproject.feature.home.generated.resources.workspace

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeNav: @Composable (NavHostController) -> Unit = {},
    viewModel: HomeViewModel = koinViewModel()
) {
    val navBottomBarController = rememberNavController()
    ChangeStatusBarColors(MaterialTheme.colorScheme.secondary)
    Scaffold(
        bottomBar = {
            BottomNavigationUI(navController = navBottomBarController)
        }) {
        homeNav(navBottomBarController)
    }
}


@Composable
fun BottomNavigationUI(
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val log = Logger.withTag("BottomNavigationUI")
   // log.d("${currentRoute.orEmpty()}  Message: ${Message::class.qualifiedName}  ${Message::class.toString()} ")

    Card(
        colors = DefaultCardColorsTheme(),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {

            val items = listOf(
                MessageRoute,
                DocumentRoute,
                WorkSpaceRoute,
                ContactsRoute,
            )
            val titles = listOf(
                Res.string.message,
                Res.string.document,
                Res.string.workspace,
                Res.string.contact
            )
            val icons = listOf(
                Res.drawable.ic_message,
                Res.drawable.ic_document,
                Res.drawable.ic_workspace,
                Res.drawable.ic_contact,
            )
            items.forEachIndexed { index, item ->
                val itemColor = if(currentRoute == item::class.qualifiedName) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                NavigationBarItem(
                    label = {
                        Text(
                            text = stringResource(titles[index]),
                            color = itemColor
                        )
                    },
                    colors = DefaultNavigationBarItemTheme(),
                    selected = item::class.qualifiedName == currentRoute,
                    icon = {
                        Icon(
                            painter = painterResource(icons[index]),
                            stringResource(titles[index]),
                            tint = itemColor
                        )
                    },
                    onClick = {
                        if (currentRoute != item::class.qualifiedName) {
                            navController.navigate(item) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    })
            }
        }
    }
}
