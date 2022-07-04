package com.example.demo.service

import com.example.demo.dto.QueryResponse
import com.example.demo.dto.TransactionRequest
import com.example.demo.model.Transaction
import com.example.demo.repository.TransactionRepository
import com.example.demo.util.Utilities
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class TransactionService(val repository:TransactionRepository) {

    fun getHello(): String {
        return "hello service"
    }
    fun save(transactionRequest:TransactionRequest): Transaction{
        val dateTime =  Utilities.convertStringToDate(transactionRequest.datetime);
        val transaction = Transaction(null,dateTime,transactionRequest.amount)
        // process datetime to
        return repository.save(transaction)
    }
    fun query(startDateStr:String,endDateStr:String):List<QueryResponse>{
        // validate
        var startDate = Utilities.convertStringToDate(startDateStr)
        var endDate = Utilities.convertStringToDate(endDateStr)

        var timezone  = ""
        if( startDateStr.indexOf("+")>0 ) {
            val datetimeArray = startDateStr.split("+")
            timezone = "+"+datetimeArray[1]
        }else if( startDateStr.indexOf("-")>0 ){
            val datetimeArray = startDateStr.split("-")
            timezone = "-"+datetimeArray[1]
        }

        val results = repository.queryGroupByHour(startDate,endDate)
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
        return list;
    }
}