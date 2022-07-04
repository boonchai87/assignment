package com.example.demo.service

import com.example.demo.dto.QueryResponse
import com.example.demo.dto.TransactionRequest
import com.example.demo.model.Transaction
import com.example.demo.repository.TransactionRepository
import com.example.demo.util.Utilities
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class TransactionServiceTest{
    val transactionRepository: TransactionRepository = mockk();
    val transactionService = TransactionService(transactionRepository);

    @Test
    fun given_ValidInput_whenCallSave_thenReturnSuccess() {
        val startDatetime = "2022-10-05T10:48:01+00:00"
        val tran1Date = Utilities.convertStringToDate(startDatetime)
        val tran1 = Transaction(null,tran1Date,1.0)


        //given
        every { transactionRepository.save(any()) } returns tran1;

        //when
        val transaction = transactionService.save(TransactionRequest(startDatetime,1.0))

        //then
        //verify(exactly = 1) { bankAccountRepository.findByIdOrNull(1) };
        assertEquals(transaction.amount,1.0)
    }

    @Test
    fun given_ValidInput_when_query_thenReturnSuccess() {
        val startDatetime = "2022-10-05T10:48:01+00:00"
        val endDatetime = "2022-11-05T18:48:02+00:00"
        val res1 = QueryResponse(startDatetime,1.0);
        val res2 = QueryResponse(endDatetime,3.0);

        val list = ArrayList<Map<String,Object>>()
        val map1 = HashMap<String,Object>()
        map1.set("year",2022.0 as Object)
        map1.set("month",10.0 as Object)
        map1.set("day", 5.0 as Object)
        map1.set("hour",10.0 as Object)
        map1.set("amount",1.1 as Object)
        val map2 = HashMap<String,Object>()
        map2.set("year",2022.0 as Object)
        map2.set("month",11.0 as Object)
        map2.set("day", 5.0 as Object)
        map2.set("hour",10.0 as Object)
        map2.set("amount",1.2 as Object)
        list.add(map1)
        list.add(map2)

        //given
        every { transactionRepository.queryGroupByHour(any(),any()) } returns list

        //when
        val result = transactionService.query(startDatetime,startDatetime)

        //then
        //verify(exactly = 1) { bankAccountRepository.findByIdOrNull(1) };
        assertEquals(result.size, 2)
    }
}