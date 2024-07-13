package com.lowbudgetlcs.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.root() {
    routing {
        route("/") {
            get {
                call.respondText("Hello world!")
            }
        }
    }
}

