package com.lowbudgetlcs

import com.lowbudgetlcs.plugins.configureRouting
import com.lowbudgetlcs.plugins.configureSerialization
import io.ktor.server.application.*
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.App")

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
    logger.info("[x] Configuring...")
    configureSerialization()
    configureRouting()
}
