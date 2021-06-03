import config.createMockClient
import config.testResponse1
import config.testResponse2
import config.testResponse3
import io.ktor.config.*
import io.ktor.http.*
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class GitlabAPIConnectorTest:WordSpec({
    val url = "https://gitlab.com/api/graphql/"
    val key = "abcd"
    val config = MapApplicationConfig(
        "ktor.environment" to "local",
        "services.local.jl.url" to url,
        "services.local.jl.key" to key,
    )
    val client = createMockClient(url,HttpMethod.Post,key,listOf(testResponse1, testResponse2, testResponse3))

    "GitlabAPIConnector" should {
        "create a valid request and return members" {

            val expectedMembers = GitlabMembers(
                    Data(
                        Group(GroupMembers(PageInfo("page0", true),
                            listOf(
                                MemberNode(User("gid://gitlab/User/8576067", "name1", "F1.S1", "f1.s1@johnlewis.co.uk"), AccessLevel("REPORTER")),
                                MemberNode(User("gid://gitlab/User/8015045", "name2", "F2.S2", "f2.s2@johnlewis.co.uk"), AccessLevel("REPORTER")
                                ))
                        ))))

            GitlabAPIConnector(config, client).getMembers("") shouldBe expectedMembers
        }
    }
}
)