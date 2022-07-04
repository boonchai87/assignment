package com.example.demo.controller

import com.example.demo.dto.QueryRequest
import com.example.demo.dto.QueryResponse
import com.example.demo.dto.TransactionRequest
import com.example.demo.dto.TransactionResponse
import com.example.demo.repository.TransactionRepository
import com.example.demo.service.TransactionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(val repository:TransactionRepository,val service:TransactionService) {

    @GetMapping("/hello")
    fun helloKotlin(): String {
        return "hello world"
    }

    @PostMapping("/save")
    // shoud validate with @valid
    fun save(@RequestBody transactionRequest: TransactionRequest):TransactionResponse{
        var status = "failed";
        try{
            service.save(transactionRequest)
            status = "success";
        }catch(e: Exception){

        }
        return TransactionResponse(status)
    }

    @PostMapping("/history")
    // validate end time must be greate starttime
    fun query(@RequestBody query: QueryRequest): List<QueryResponse>{
        return service.query(query.startDatetime,query.endDatetime);
    }
}