package com.lowbudgetlcs.messageq

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.slf4j.LoggerFactory

object RabbitMQBridge {
    private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.messageq.ConnectionManager")
    private const val EXCHANGE_NAME = "RIOT_CALLBACKS"
    private val factory = ConnectionFactory().apply {
        host = System.getenv("MESSAGEQ_HOST") ?: "rabbitmq"
        isAutomaticRecoveryEnabled = true
        networkRecoveryInterval = 15000
    }

    private val connection: Connection by lazy {
        factory.newConnection().also {
            logger.debug("Created new messageq connection.")
        }
    }

    private val channel: Channel by lazy {
        connection.createChannel().apply {
            exchangeDeclare(EXCHANGE_NAME, "topic")
        }.also {
            logger.debug("Created new messageq channel.")
        }
    }

    fun emit(routingKeys: Array<String>, message: String) {
        channel.basicPublish(EXCHANGE_NAME, routingKeys.joinToString("."), null, message.toByteArray(charset("UTF-8")))
            .also {
                logger.debug("Broadcast {} on {} topics.", message, routingKeys)
            }
    }
}
