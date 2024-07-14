package com.lowbudgetlcs.routes

import com.lowbudgetlcs.Result
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.riot() {
    routing {
        route("/riot") {
            post("/game-end") {
                // Verify callback data
                try {
                    val body: Result = call.receive<Result>()
                    // Respond
                    call.respond(HttpStatusCode.OK)
                    // Asynch publish callback on rabbitmq
                    // Asynch invoke tournament engine
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest, call.receive())
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest, call.receive())
                }

            }
        }
    }
}