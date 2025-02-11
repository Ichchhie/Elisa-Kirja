package API

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.AllBooksContainer
import models.BookContainer

class BooksService {

    suspend fun retrieveBooksFromAPI(id: String?): BookContainer? {

        val client: HttpClient by lazy {
            val config: HttpClientConfig<*>.() -> Unit = {
                expectSuccess = true
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            prettyPrint = true
                            isLenient = true
                        }
                    )
                }
            }
            HttpClient(config)
        }

        val response = runCatching {
//            client.get("https://api.codetabs.com/v1/proxy/?quest=https://kirja.elisa.fi/api/books/$id")
//                .body<BookContainer>()
            //changing because the previous API no longer exists
            client.get("https://api.codetabs.com/v1/proxy/?quest=https://api.jsonbin.io/v3/b/$id")
                .body<BookContainer>()
        }
        return response.getOrNull()
    }

    suspend fun retrieveAllBooksOfCategory(id: String?): AllBooksContainer? {
        val client: HttpClient by lazy {
            val config: HttpClientConfig<*>.() -> Unit = {
                expectSuccess = true
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            prettyPrint = true
                            isLenient = true
                        }
                    )
                }
            }
            HttpClient(config)
        }

        val response = runCatching {
            client.get("https://api.codetabs.com/v1/proxy/?quest=https://api.jsonbin.io/v3/b/6631d9aee41b4d34e4ed2278")
                .body<AllBooksContainer>()
        }
        return response.getOrNull()
    }
}