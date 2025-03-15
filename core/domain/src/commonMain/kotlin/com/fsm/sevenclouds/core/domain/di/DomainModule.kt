package com.fsm.sevenclouds.core.domain.di

import com.fsm.sevenclouds.core.data.di.dataModule
import org.koin.dsl.module

val domainModule = module {
    includes(dataModule)

    //factoryOf(::GetArtsUseCase)

}