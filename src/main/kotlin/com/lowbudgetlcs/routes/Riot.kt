package com.lowbudgetlcs.routes

import com.lowbudgetlcs.data.MatchResult
import com.lowbudgetlcs.messageq.RabbitMQBridge
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

fun Application.riot() {
    val logger = LoggerFactory.getLogger("com.lowbudgetlcs.routes.Riot")
    routing {
        route("/riot") {
            post("/callback") {
                try {
                    val body: MatchResult = call.receive<MatchResult>()
                    logger.debug("[x] Recieved {}", body)
                    call.respond(HttpStatusCode.OK)
                    // Save MatchResult to sqlite
                    //launch {}
                    // Asynch publish callback on MQ
                    launch {
                        RabbitMQBridge.emit(
                            arrayOf("callback"),
                            body.toString()
                        ).also {
                            logger.debug("Successfully published callback")
                        }
                    }
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                    logger.error(ex.message, ex)
                } catch (ex: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest)
                    logger.error(ex.message, ex)
                } catch (ex: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                    logger.error(ex.message, ex)
                }
            }
        }
    }
}

