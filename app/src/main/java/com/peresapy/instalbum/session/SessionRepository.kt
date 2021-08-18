package com.peresapy.instalbum.session

interface SessionRepository {

    fun getSession(): Session?

    fun saveSession(session: Session)
}