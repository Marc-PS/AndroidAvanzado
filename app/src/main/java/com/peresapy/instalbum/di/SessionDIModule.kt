package com.peresapy.instalbum.di

import com.peresapy.instalbum.session.SessionLocalDataSource
import com.peresapy.instalbum.session.SessionMemoryDataSource
import com.peresapy.instalbum.session.SessionRepository
import com.peresapy.instalbum.session.SessionRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

object SessionDIModule : DIBaseModule("SessionDIModule") {

    override val builder: DI.Builder.() -> Unit = {

        bind<SessionLocalDataSource>() with singleton {
            SessionLocalDataSource((instance()))
        }

        bind<SessionMemoryDataSource>() with singleton {
            SessionMemoryDataSource()
        }

        bind<SessionRepository>() with singleton {
            SessionRepositoryImpl(instance(), instance())
        }
    }
}