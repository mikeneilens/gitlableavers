package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.http.*

fun createMockClient(expectedUrl:String, expectedMethod: HttpMethod, expectedKey:String, responses:List<String>, responseCode: HttpStatusCode = HttpStatusCode.OK )  =
    HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                val responseHeaders = headersOf("Content-Type" to  listOf(ContentType.Application.Json.toString()))
                if (request.url.toString().removeSuffix("/") == expectedUrl.removeSuffix("/") && request.method == expectedMethod && request.headers.get("PRIVATE-TOKEN") == expectedKey ) {
                    val bodyText = request.body.toByteArray().map{it.toChar()}.joinToString("")
                    if (responses.size == 1) {
                        respond(responses[0], status = responseCode,  headers = responseHeaders)
                    }
                    else if (bodyText.contains("page0")) {
                        respond(responses[1], status = responseCode,  headers = responseHeaders)
                    } else if (bodyText.contains("page1")) {
                        respond(responses[2], status = responseCode,  headers = responseHeaders)
                    } else {
                        respond(responses[0], status = responseCode,  headers = responseHeaders)
                    }
                } else {
                    respond(responses[0], status = HttpStatusCode.BadRequest,  headers = responseHeaders)
                }
            }
        }
        install(JsonFeature) {
            serializer = JacksonSerializer(jacksonObjectMapper(), ObjectMapper::jacksonConfiguration)
        }
    }