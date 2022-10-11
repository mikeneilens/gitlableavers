typealias CreatedAt = String

data class ResponseModel (val id: String, val name: String, val username: String, val publicEmail: String, val createdAt: CreatedAt) {
    companion object {
        fun fromMemberNode(memberNode:MemberNode): ResponseModel {
            return ResponseModel(
                id = memberNode.user?.id ?: "",
                name = memberNode.user?.name ?: "",
                username = memberNode.user?.username ?: "",
                publicEmail = memberNode.user?.publicEmail ?: memberNode.user?.id ?: "",
                createdAt = memberNode.createdAt ?: ""
            )
        }
    }
    fun toFormattedOutput():String = "$publicEmail\t${publicEmail.toClarityName()}\t$createdAt"
}

fun String.nameWithinEmail() = split("@").first().split(".")

fun String.toClarityName() =
    if (nameWithinEmail().size ==2 ) "${nameWithinEmail().last()}, ${nameWithinEmail().first()}" else ""
