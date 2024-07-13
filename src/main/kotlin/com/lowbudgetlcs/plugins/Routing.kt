package com.lowbudgetlcs.plugins

import io.ktor.server.application.*

import com.lowbudgetlcs.routes.*

fun Application.configureRouting() {
    root()
    riot()
}
