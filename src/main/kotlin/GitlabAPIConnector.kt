import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.config.*
import io.ktor.http.*

class Query(val query:String)

class GitlabAPIConnector (
    val config: ApplicationConfig,
    private val client: HttpClient
    ) {
    private val env = config.property("ktor.environment").getString()
    private val baseUrl = config.property("services.$env.jl.url").getString()
    private val key = config.property("services.$env.jl.key").getString()

    suspend fun getMembers(endCursor:String = "") = client.post<GitlabMembers>(baseUrl) {
        headers {
            append("PRIVATE-TOKEN", key)
            append(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        body=Query(
            """
                query {
                  group(fullPath: "JohnLewisPartnership") {
                    groupMembers(relations: DIRECT, first: 100, after:"$endCursor") {
                      pageInfo {
                        endCursor
                        hasNextPage
                      }
                      nodes {
                        user {
                          id
                          name
                          username
                          publicEmail
                        }
                        accessLevel {
                          stringValue
                        }
                      }
                    }
                  }
                }
            """.trimIndent()
        )
    }
}

data class GitlabMembers(val data: Data?)

data class AccessLevel(val stringValue: String?)
data class Data(val group: Group?)
data class Group(val groupMembers: GroupMembers?)
data class GroupMembers(val pageInfo: PageInfo?, val nodes: List<MemberNode>?)
data class MemberNode(val user: User?, val accessLevel: AccessLevel?)
data class PageInfo(val endCursor: String?, val hasNextPage: Boolean?)
data class User(val id: String?, val name: String?, val username: String?, val publicEmail: String?)
