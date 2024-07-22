package com.lowbudgetlcs.plugins

import com.lowbudgetlcs.routes.riot
import com.lowbudgetlcs.routes.root
import io.ktor.server.application.*
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.plugins.Routing")

fun Application.configureRouting() {
    root()
    riot()
    logger.debug("Routing configured.")
}
