package com.lowbudgetlcs.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

fun Application.root() {
    routing {
        route("/") {
            get {
                call.respondText("Hello world!")
            }
        }
    }
}

