package com.example.demo.repository

import com.example.demo.dto.QueryResponse
import com.example.demo.model.Transaction
import com.example.demo.util.Utilities
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.math.BigInteger
import org.assertj.core.api.Assertions.assertThat
import java.util.Date

@DataJpaTest
// https://stackoverflow.com/questions/41315386/spring-boot-1-4-datajpatest-error-creating-bean-with-name-datasource
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val transactionRepository: TransactionRepository) {

    @Test
    fun `When queryGroupByHour then return List of `() {
        val startDatetime = "2022-10-05T10:48:01+00:00"
        val endDatetime = "2022-11-05T18:48:02+00:00"

        val tran1Date = Utilities.convertStringToDate(startDatetime)
        val tran2Date = Utilities.convertStringToDate(endDatetime)

        val tran1 = Transaction(null,tran1Date,1.0)
        entityManager.persist(tran1)

        val tran2 = Transaction(null,tran2Date,3.0)
        entityManager.persist(tran2)
        entityManager.flush()



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
            val amount = result.get("amount") as Double
            list.add(QueryResponse(datetime,amount.toDouble()) )
        }
        assertThat(list.size).isEqualTo(2);
    }
}