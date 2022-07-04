package com.example.demo.repository

import com.example.demo.dto.QueryResponse
import com.example.demo.repository.TransactionRepository
import com.example.demo.util.Utilities
import org.hibernate.hql.internal.QueryExecutionRequestException
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigInteger

@DataJpaTest
// https://stackoverflow.com/questions/41315386/spring-boot-1-4-datajpatest-error-creating-bean-with-name-datasource
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val transactionRepository: TransactionRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val startDatetime = "2019-10-05T10:48:01+00:00"
        val endDatetime = "2019-11-05T18:48:02+00:00"

        var timezone  = ""
        if( startDatetime.indexOf("+")>0 ) {
            val datetimeArray = startDatetime.split("+")
            timezone = "+"+datetimeArray[1]
        }else if( startDatetime.indexOf("-")>0 ){
            val datetimeArray = startDatetime.split("-")
            timezone = "-"+datetimeArray[1]
        }

        val start = Utilities.convertStringToDate(startDatetime)
        val end = Utilities.convertStringToDate(endDatetime)

        val results = transactionRepository.queryGroupByHour(start,end)
        var list = ArrayList<QueryResponse>();
        for(result : Map<String,Object> in results){
            val year = (result.get("year")as Double).toString().replace(".0","")
            val month = (result.get("month")as Double).toString().replace(".0","")
            val day = (result.get("day")as Double).toString().replace(".0","")
            val hour = (result.get("hour")as Double).toString().replace(".0","")

            var datetime = year+"-"+month+"-"+day+"T"+hour.toString()+":00:00"+timezone;
            val amount = result.get("amount") as BigInteger
            list.add(QueryResponse(datetime,amount.toDouble()) )
            //println(result.get("datetime"))
            //println(result.get("amount"))
        }
        println(list)
//        val juergen = User("springjuergen", "Juergen", "Hoeller")
//        entityManager.persist(juergen)
//        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
//        entityManager.persist(article)
//        entityManager.flush()
//        val found = articleRepository.findByIdOrNull(article.id!!)
//        assertThat(found).isEqualTo(article)
    }

    /*@Test
    fun `When findByLogin then return User`() {
//        val juergen = User("springjuergen", "Juergen", "Hoeller")
//        entityManager.persist(juergen)
//        entityManager.flush()
//        val user = userRepository.findByLogin(juergen.login)
//        assertThat(user).isEqualTo(juergen)
    }*/
}