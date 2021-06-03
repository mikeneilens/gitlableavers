import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MembersToNamesTest: StringSpec( {
    "toNames returns an empty list if the list of members is empty" {
        emptyList<User>().toNames() shouldBe emptyList()
    }
    "toNames returns a list containing one name if the list of members contains one user" {
        val user1 = User("1","name1", "name 1", "f1.s1@waitrose.com")

        val result = listOf(user1).toNames()
        result.size shouldBe 1
        result[0] shouldBe "f1.s1@waitrose.com	s1, f1"
    }
    "toNames returns a list containing three names if the list of members contains three users" {
        val user1 = User("1","name1", "name 1", "f1.s1@waitrose.com")
        val user2 = User("2","name2", "name 2", "f2.s2@waitrose.com")
        val user3 = User("3","name3", "name 3", "f3.s3@waitrose.com")

        val result = listOf(user1,user2,user3).toNames()
        result.size shouldBe 3
        result[0] shouldBe "f1.s1@waitrose.com	s1, f1"
        result[1] shouldBe "f2.s2@waitrose.com	s2, f2"
        result[2] shouldBe "f3.s3@waitrose.com	s3, f3"
    }

})