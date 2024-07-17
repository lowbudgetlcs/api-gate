package com.lowbudgetlcs.routes

import com.lowbudgetlcs.data.MatchResult
import com.lowbudgetlcs.messageq.Publisher
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
                    logger.debug("[x] Recieved valid post-game callback")
                    // Save MatchResult to sqlite
                    // Asynch publish callback on MQ
                    launch {
                        Publisher().emit(
                            System.getenv("EXCHANGE_NAME") ?: "RIOT_CALLBACKS",
                            body.toString(),
                            arrayOf("callback")
                        ).also {
                            logger.debug("Successfully published callback")
                        }
                    }
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

