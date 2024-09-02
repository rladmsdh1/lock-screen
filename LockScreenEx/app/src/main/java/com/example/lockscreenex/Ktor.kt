package com.example.lockscreenex

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object KtorClient {

    //http 클라이언트
    val httpClient = HttpClient {
        // json 설정
        install(ContentNegotiation) {
            json()
        }

        // 로깅 설정
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {

               //     Log.d("test", "api log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 10000
        }
    }
}

object Ktor {
    suspend fun getResponse(): List<Post> =
        KtorClient.httpClient.get("https://jsonplaceholder.typicode.com/posts").body()
}
