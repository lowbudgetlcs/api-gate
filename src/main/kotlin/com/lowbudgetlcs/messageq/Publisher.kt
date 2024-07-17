package com.lowbudgetlcs.messageq

import com.lowbudgetlcs.messageq.ConnectionManager.connection
import org.slf4j.LoggerFactory
import java.nio.charset.StandardCharsets

class Publisher() {
    fun emit(exchange: String, message: String, topics: Array<String>) {
        connection.createChannel().use { channel ->
            channel.basicPublish(
                exchange, topics.joinToString("."), null, message.toByteArray(StandardCharsets.UTF_8)
            )
            logger.debug("[x] Broadcasted {} to {}.", message, topics.joinToString("."))
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.Publisher")
    }
}
