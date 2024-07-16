package com.lowbudgetlcs.plugins

import io.ktor.server.application.*

import com.lowbudgetlcs.routes.*
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.plugins.Routing")

fun Application.configureRouting() {
    root()
    riot()
    logger.debug("Routing configured.")
}
