package com.lowbudgetlcs.routes

import com.lowbudgetlcs.Publisher.emit
import com.lowbudgetlcs.data.Result
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

fun Application.riot() {
    val logger = LoggerFactory.getLogger("com.lowbudgetlcs.routes.Riot")
    routing {
        route("/riot") {
            post("/callback") {
                // Verify callback data
                try {
                    val body: Result = call.receive<Result>()
                    logger.debug(body.toString())
                    // Respond
                    call.respond(HttpStatusCode.OK)
                    // Asynch publish callback on rabbitmq
                    emit("RIOT_CALLBACKS", body.toString(), arrayOf("callback"))
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest, call.receive())
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest, call.receive())
                } catch (ex: BadRequestException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}

