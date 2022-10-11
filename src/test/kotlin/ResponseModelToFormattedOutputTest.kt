import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResponseModelToFormattedOutputTest: StringSpec( {
    "toFormattedOutput returns an empty list if the list of members is empty" {
        emptyList<ResponseModel>().toFormattedOutput() shouldBe emptyList()
    }
    "toFormattedOutput returns a list containing one name if the list of members contains one user" {
        val user1 = ResponseModel("1","name1", "name 1", "f1.s1@waitrose.com", "t1")

        val result = listOf(user1).toFormattedOutput()
        result.size shouldBe 1
        result[0] shouldBe "f1.s1@waitrose.com	s1, f1\tt1"
    }
    "toFormattedOutput returns a list containing three names if the list of members contains three users" {
        val user1 = ResponseModel("1","name1", "name 1", "f1.s1@waitrose.com","t1")
        val user2 = ResponseModel("2","name2", "name 2", "f2.s2@waitrose.com", "t2")
        val user3 = ResponseModel("3","name3", "name 3", "f3.s3@waitrose.com", "t3")

        val result = listOf(user1,user2,user3).toFormattedOutput()
        result.size shouldBe 3
        result[0] shouldBe "f1.s1@waitrose.com	s1, f1\tt1"
        result[1] shouldBe "f2.s2@waitrose.com	s2, f2\tt2"
        result[2] shouldBe "f3.s3@waitrose.com	s3, f3\tt3"
    }

})