package com.lowbudgetlcs

import com.rabbitmq.client.ConnectionFactory
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets

object Publisher {

    fun emit(exchange: String, message: String, topics: Array<String>) {
        val logger = LoggerFactory.getLogger("com.lowbudgetlcs.routes.Riot")
        ConnectionFactory().apply {
            host = System.getenv("HOST") ?: "localhost"
        }.newConnection().use { conn ->
            conn.createChannel().use { channel ->
                channel.basicPublish(
                    exchange,
                    topics.joinToString(","),
                    null,
                    message.toByteArray(StandardCharsets.UTF_8)
                )
                logger.debug("[x] Broadcasted {} to {}.", message, topics.joinToString(","))
            }
        }
    }
}