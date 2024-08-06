package com.lowbudgetlcs.routes

import com.lowbudgetlcs.data.MatchResult
import com.lowbudgetlcs.messageq.RabbitMQBridge
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

fun Application.riot() {
    val logger = LoggerFactory.getLogger("com.lowbudgetlcs.routes.Riot")
    routing {
        route("/riot") {
            get("/callback") {
                call.respond(HttpStatusCode.OK, "Hello world!")
            }
            post("/callback") {
                try {
                    logger.info("[x] Recieved POST on {}", call.request.uri)
                    val body: MatchResult = call.receive<MatchResult>()
                    call.respond(HttpStatusCode.OK)
                    val message = Json.encodeToString(body)
                    logger.debug("MatchResult: {}", message)
                    // Save MatchResult to sqlite
                    // Asynch publish callback on MQ
                    launch {
                        RabbitMQBridge.emit(
                            arrayOf("callback"),
                            message
                        ).also {
                            logger.debug("Successfully published {} on [callback] topic.", message)
                        }
                    }
                } catch (ex: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest)
                    logger.error("Could not transform request body on {}.", call.request.uri)
                    logger.error(ex.message)
                }
            }
        }
    }
}

