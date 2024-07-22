package com.lowbudgetlcs.messageq

import com.rabbitmq.client.AlreadyClosedException
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.slf4j.LoggerFactory

object RabbitMQBridge {
    private const val EXCHANGE_NAME = "RIOT_CALLBACKS"
    private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.messageq.ConnectionManager")
    private val factory = ConnectionFactory().apply {
        host = System.getenv("MESSAGEQ_HOST") ?: "localhost"
        isAutomaticRecoveryEnabled = true
        networkRecoveryInterval = 15000
    }

    private val lazyManager = resettableManager()

    private val connection: Connection by resettableLazy(lazyManager) {
        factory.newConnection().also {
            logger.debug("[x] Created new messageq connection.")
        }
    }

    private val channel: Channel by resettableLazy(lazyManager) {
        connection.createChannel().apply {
            exchangeDeclare(EXCHANGE_NAME, "topic")
        }.also {
            logger.debug("[x] Created new messageq channel.")
        }
    }

    fun emit(routingKey: Array<String>, message: String) {
        var retries = System.getenv("MESSAGEQ_RETRIES")?.toInt() ?: 5
        while (retries > 0)
            try {
                channel.basicPublish(
                    EXCHANGE_NAME,
                    routingKey.joinToString("."),
                    null,
                    message.toByteArray(charset("UTF-8"))
                )
                    .also {
                        retries = -1
                        logger.debug("[x] Broadcast {} on {}", message, routingKey)
                    }
            } catch (ex: AlreadyClosedException) {
                refreshConnection()
                retries -= 1
            }
    }

    private fun refreshConnection() {
        lazyManager.reset()
    }
}
