package com.fsm.sevenclouds.di

import coil3.annotation.ExperimentalCoilApi
import coil3.network.CacheStrategy
import coil3.network.NetworkFetcher
import coil3.network.ktor.asNetworkClient
import com.fsm.sevenclouds.core.domain.di.domainModule
import com.fsm.sevenclouds.feature.home.di.homeModule
import com.fsm.sevenclouds.feature.login.di.loginModule
import com.fsm.sevenclouds.feature.message.di.messageModule
import com.fsm.sevenclouds.feature.scan.di.scanModule
import io.ktor.client.HttpClient
import org.koin.dsl.module

@OptIn(ExperimentalCoilApi::class)
val appModule = module {
    includes(loginModule, homeModule, scanModule, messageModule)
    includes(domainModule)
    single {
        NetworkFetcher.Factory(
            networkClient = { get<HttpClient>().asNetworkClient() },
            cacheStrategy = { CacheStrategy() },
        )
    }
}