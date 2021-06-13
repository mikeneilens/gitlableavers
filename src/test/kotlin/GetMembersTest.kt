import config.createMockClient
import config.testResponse1
import config.testResponse2
import config.testResponse3
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.config.*
import io.ktor.http.*

class GetMembersTest: StringSpec( {
    val url = "https://gitlab.com/api/graphql/"
    val key = "abcd"
    val config = MapApplicationConfig(
        "ktor.environment" to "local",
        "services.local.jl.url" to url,
        "services.local.jl.key" to key,
    )

    "getMembers returns a list of two members if there is only one page of members with two members on each page" {
        val client = createMockClient(url, HttpMethod.Post,key,listOf(testResponse3))
        val result = getMembers(config, client)
        result.size shouldBe 2
        result[0].publicEmail shouldBe "f5.s5@johnlewis.co.uk"
        result[1].publicEmail shouldBe "f6.s6@johnlewis.co.uk"
    }
    "getMembers returns a list of six members if there is three pages of members with two members on each page"  {
        val client = createMockClient(url, HttpMethod.Post,key,listOf(testResponse1, testResponse2, testResponse3))
        val result = getMembers(config, client)
        result.size shouldBe 6
        result[0].publicEmail shouldBe "f1.s1@johnlewis.co.uk"
        result[1].publicEmail shouldBe "f2.s2@johnlewis.co.uk"
        result[2].publicEmail shouldBe "f3.s3@johnlewis.co.uk"
        result[3].publicEmail shouldBe "f4.s4@johnlewis.co.uk"
        result[4].publicEmail shouldBe "f5.s5@johnlewis.co.uk"
        result[5].publicEmail shouldBe "f6.s6@johnlewis.co.uk"
    }
})