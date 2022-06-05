package data.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.util.*

object DefaultHttpClient {

    private const val BASE_URL = "https://api.com"
    private const val SECRET = "lamti.levelup"
    private const val BASIC = "Basic"
    private const val TIME_OUT = 60_000L

    val client = HttpClient(CIO) {
        defaultRequest {
            val credentials = Base64.getEncoder().encodeToString(SECRET.toByteArray())
            header(HttpHeaders.ProxyAuthorization, "$BASIC $credentials")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            url.takeFrom(URLBuilder().takeFrom(BASE_URL).apply {
                encodedPath += url.encodedPath
            })
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(
                json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }
            )
            engine {
                requestTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("Logger Ktor: $message")
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status: ${response.status.value}")
            }
        }
    }
}