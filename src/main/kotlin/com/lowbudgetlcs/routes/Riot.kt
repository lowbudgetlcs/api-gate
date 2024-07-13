package com.lowbudgetlcs.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import java.lang.Math.random

fun Application.riot() {
    routing {
        route("/riot") {
            get("/game-end") {
                // Verify callback data
                val result = async { getData() }
                // Respond
                call.respond(HttpStatusCode.OK, "Result: ${result.await()}")
                // Asynch publish callback on rabbitmq
                // Asynch invoke tournament engine
            }
        }
    }
}

suspend fun getData(): String {
    val delay: Long = (random() * 10000).toLong()
    delay(delay)
    return "Waited ${delay / 1000.0} second${if (delay == 1L) "s" else ""}."
}