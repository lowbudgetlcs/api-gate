package com.lowbudgetlcs.messageq

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.slf4j.LoggerFactory

object ConnectionManager {
    private val logger = LoggerFactory.getLogger("com.lowbudgetlcs.messageq.ConnectionPool")

    //    private val poolSize: Int = try {
//        System.getenv("CONNECTION_POOL_SIZE").toInt()
//    } catch (ex: NumberFormatException) {
//        5
//    }
//    private val connectionPool: ArrayDeque<Connection> = ArrayDeque(poolSize)
    val connection: Connection by lazy {
        ConnectionFactory().apply {
            host = System.getenv("MESSAGEQ_HOST") ?: "localhost"
            isAutomaticRecoveryEnabled = true
            networkRecoveryInterval = 15000
        }.newConnection().also {
            logger.debug("Connected to messageq.")
            connection.createChannel().use { channel ->
                channel.exchangeDeclare(System.getenv("EXCHANGE_NAME") ?: "RIOT_CALLBACKS", "topic")
            }
        }
    }
}