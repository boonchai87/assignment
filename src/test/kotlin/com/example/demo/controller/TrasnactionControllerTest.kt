package com.example.demo.controller

import com.example.demo.dto.QueryRequest
import com.example.demo.dto.QueryResponse
import com.example.demo.dto.TransactionRequest
import com.example.demo.model.Transaction
import com.example.demo.repository.TransactionRepository
import com.example.demo.service.TransactionService
import com.example.demo.util.Utilities
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class TransactionControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var transactionService: TransactionService

    @MockkBean
    private lateinit var transactionRepository: TransactionRepository

    @Test
    fun `save transaction success`() {
        val tranRequest = TransactionRequest( "2022-10-05T10:48:01+00:00", 1.0)
        val startDatetime = "2022-10-05T10:48:01+00:00"
        val tran1Date = Utilities.convertStringToDate(startDatetime)
        val tran1 = Transaction(null,tran1Date,1.0)

        every { transactionService.save(tranRequest) } returns tran1// Unit = void
        mockMvc.perform(post("/save")
            .content(asJsonString(tranRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.status").value("success"))
    }

    @Test
    fun `get  history success`() {
        val startDatetime = "2022-10-05T10:48:01+00:00"
        val endDatetime = "2022-11-05T18:48:02+00:00"
        val res1 = QueryResponse(startDatetime,1.0);
        val res2 = QueryResponse(endDatetime,3.0);

        val request = QueryRequest(startDatetime,endDatetime)

        every { transactionService.query(any(),any()) } returns listOf(res1, res2)
        mockMvc.perform(post("/history")
            .content(asJsonString(request))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].amount").value(1.0))
            .andExpect(jsonPath("\$.[1].amount").value(3.0))
    }

    fun asJsonString(obj: Any?): String {
        return try {
            ObjectMapper().writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}