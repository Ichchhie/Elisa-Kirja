package API

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.BookContainer
import models.Books

class GetBooks {

    suspend fun retrieveBooksFromAPI(): BookContainer? {

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

        val response = runCatching { client.get("https://kirja.elisa.fi/api/books/211203").body<BookContainer>() }
        return response.getOrNull()

        /*
        val client = HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
         return try {
            val response: HttpResponse = client.get("https://kirja.elisa.fi/api/books/211203") {
                 method = HttpMethod.Get
                 headers {
                     append(HttpHeaders.Accept, "application/json")
                 }
             }
             response.bodyAsText()

         } catch (e: Exception) {
             "Error fetching books: ${e.message}"
         } finally {
             client.close()
         }
         */

    }

}