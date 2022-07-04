package com.example.demo.repository

import com.example.demo.dto.QueryResponse
import com.example.demo.dto.TransactionRequest
import com.example.demo.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Date
import javax.persistence.SqlResultSetMapping

@Repository
public interface TransactionRepository : JpaRepository<Transaction,Long>{

    @Query(value="select date_part('year',datetime) as year,date_part('month',datetime) as month,date_part('day',datetime) as day ,date_part('hour',datetime) as hour , count(amount) as amount " +
            " from Transaction t " +
            " where t.datetime>=?1 and t.datetime<=?2 " +
            " group by date_part('year',datetime),date_part('month',datetime),date_part('day',datetime),date_part('hour',datetime) "
        ,nativeQuery = true)
    fun queryGroupByHour(startDate: Date,endDate: Date):List<Map<String,Object>>

}