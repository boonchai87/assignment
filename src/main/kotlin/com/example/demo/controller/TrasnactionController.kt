package com.example.demo.controller

import com.example.demo.dto.QueryRequest
import com.example.demo.dto.QueryResponse
import com.example.demo.dto.TransactionRequest
import com.example.demo.dto.TransactionResponse
import com.example.demo.repository.TransactionRepository
import com.example.demo.service.TransactionService
import com.example.demo.util.Utilities
import io.mockk.InternalPlatformDsl.toStr
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TrasnactionController(val repository:TransactionRepository, val service:TransactionService) {

    @GetMapping("/hello")
    fun helloKotlin(): String {
        return "hello world"
    }

    @PostMapping("/save")
    fun save(@Valid @RequestBody transactionRequest: TransactionRequest,bindingResult: BindingResult):TransactionResponse{
        var status = "failed"
        var message =""
        try{
            if( !Utilities.isValidAmount(transactionRequest.amount) ){
                message = "inValid amount"
            }else if( !Utilities.isValidDatePattern(transactionRequest.datetime) ){
                message = "inValid date parttern"
            }else{
                service.save(transactionRequest)
                status = "success"
            }
        }catch(e: Exception){
            println(e)
        }
        return TransactionResponse(status,message)
    }

    @PostMapping("/history")
    fun query(@RequestBody @Valid query: QueryRequest): List<QueryResponse>{
        if( Utilities.checkStartDateMustLowerThanEndDate(query.startDatetime,query.endDatetime) ){
            return service.query(query.startDatetime,query.endDatetime)
        }else{
            return ArrayList<QueryResponse>()// blank
        }
    }

}