package com.fsm.sevenclouds.core.common

import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Serializable object ServerSetRoute

@Serializable object HomeRoute
@Serializable object MessageRoute
@Serializable object DocumentRoute
@Serializable object WorkSpaceRoute
@Serializable object ContactsRoute


@Serializable object ScanRoute
@Serializable
data class ChatRoute(val id: Long)