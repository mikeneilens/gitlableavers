import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.testing.*
import config.createMockClient
import config.testResponse1
import config.testResponse2
import config.testResponse3
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class RouteTest:WordSpec({
    "A request to this service" should {
        "return a list of users that may need removing" {

            withTestApplication( {
                val url = "https://gitlab.com/api/graphql/"
                val key = "abcd"
                val client = createMockClient(url, HttpMethod.Post, key,listOf(testResponse1, testResponse2, testResponse3))
                (environment.config as MapApplicationConfig).apply {
                    put("ktor.environment", "local")
                    put("services.local.jl.url", url)
                    put("services.local.jl.key", key)
                }
                mainModule(client)
            }) {
                val call = handleRequest(HttpMethod.Get, "/")
                val expectedResult = """
                    f1.s1@johnlewis.co.uk	s1, f1	123
                    f2.s2@johnlewis.co.uk	s2, f2	123
                    f3.s3@johnlewis.co.uk	s3, f3	123
                    f4.s4@johnlewis.co.uk	s4, f4	123
                    f5.s5@johnlewis.co.uk	s5, f5	123
                    f6.s6@johnlewis.co.uk	s6, f6	123
                """.trimIndent()
                call.response.status() shouldBe HttpStatusCode.OK
                call.response.content shouldBe expectedResult
            }
        }
    }
})