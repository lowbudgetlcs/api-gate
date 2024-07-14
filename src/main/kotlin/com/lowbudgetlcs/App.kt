package com.lowbudgetlcs

import com.lowbudgetlcs.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 1337, host = "0.0.0.0", module = Application::main)
        .start(wait = true)
}

fun Application.main() {
    configureSerialization()
    configureDatabases()
    configureRouting()
}
