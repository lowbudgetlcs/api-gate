package com.lowbudgetlcs

import com.lowbudgetlcs.data.Game
import com.lowbudgetlcs.data.MatchResult
import com.lowbudgetlcs.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(InternalAPI::class)
class APIRouteTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello world!", bodyAsText())
        }
        client.get("/riot/callback").apply {
            assertEquals(HttpStatusCode.NotFound, status)
        }
        val game = Game(2, 4)
        val result = MatchResult(1, "abc", game, 3)
        client.post("/riot/callback") {
            body = result
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
