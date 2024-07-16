package com.lowbudgetlcs

import com.lowbudgetlcs.plugins.configureRouting
import com.lowbudgetlcs.plugins.configureSerialization
import com.rabbitmq.client.ConnectionFactory
import io.ktor.server.application.*
import org.slf4j.LoggerFactory

private const val EXCHANGE_NAME = "RIOT_CALLBACKS"
private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.App")
fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

fun connectMessageQ() {
    ConnectionFactory().apply {
        host = System.getenv("HOST") ?: "localhost"
    }.newConnection().use { conn ->
        conn.createChannel().use { channel ->
            channel.exchangeDeclare(EXCHANGE_NAME, "topic")
        }
    }
}

fun Application.module() {
    logger.info("[x] Starting embedded server...")
    configureSerialization()
    configureRouting()
    logger.info("[x] Connecting to rabbitmq...")
    connectMessageQ()
}
