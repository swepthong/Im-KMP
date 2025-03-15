package com.fsm.sevenclouds.core.network.di

import co.touchlab.kermit.Logger
import com.fsm.sevenclouds.core.network.SevencloudsNetworkDataSource
import com.fsm.sevenclouds.core.network.ktor.KtorSevencloudsNetwork
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger as kLogger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val networkModule = module {
    singleOf(::KtorSevencloudsNetwork) { bind<SevencloudsNetworkDataSource>() }
    single {
        Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
            encodeDefaults = true
            classDiscriminator = "#class"
        }
    }
    single {
        HttpClient {
            val log = Logger.withTag("network")
            followRedirects = true
            expectSuccess = false
            install(HttpTimeout) {
                val timeout = 60000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
            install(ContentNegotiation) {
                json(get())
            }
            install(ResponseObserver) {
                onResponse { response ->
                    log.d("AppDebug HTTP ResponseObserver status: ${response.status.value}")
                }
            }
            install(HttpCache)
            install(Logging) {
                level = LogLevel.ALL
                logger = object : kLogger {
                    override fun log(message: String) {
                        log.d("AppDebug KtorHttpClient message:$message")
                    }
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
             /*   url {
                    protocol = URLProtocol.HTTP
                    host = sevenclouds_HOST
                    port = sevenclouds_PORT
                    path(sevenclouds_PATH)
                    //parameters.append(KEY, BuildConfig.RIJKSMUSEUM_API_KEY)
                    //parameters.append(HAS_IMAGE, true.toString())
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                val someService: PreferenceService = get()
                val token = someService.retrieveData(TOKEN)
                log.d("token: $token")
                if (token != null) {
                    header("token", token)
                }*/
            }
        }
    }
}