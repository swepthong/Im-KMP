package com.fsm.sevenclouds

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform