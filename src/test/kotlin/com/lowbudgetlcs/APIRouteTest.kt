package com.lowbudgetlcs

import com.lowbudgetlcs.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

//@OptIn(InternalAPI::class)
class APIRouteTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
            // configureSerialization()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello world!", bodyAsText())
        }

        /*client.get("/riot/callback").apply {
            assertEquals(HttpStatusCode.MethodNotAllowed, status)
        }
        val game = Game(2, 4)
        val result = MatchResult(1, "abc", game, 3)
        client.post("/riot/callback") {
            headers.append(HttpHeaders.ContentType, ContentType.Application.Json)
            body = result
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }*/
    }
}
