package com.lowbudgetlcs.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.plugins.Serialization")

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    logger.debug("Serialization configured.")
}
