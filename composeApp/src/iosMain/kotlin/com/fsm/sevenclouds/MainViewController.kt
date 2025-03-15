package com.fsm.sevenclouds

import androidx.compose.ui.window.ComposeUIViewController
import com.fsm.sevenclouds.di.appModule

import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule/*, keyValueModule*/)
        }
    }
) { App() }