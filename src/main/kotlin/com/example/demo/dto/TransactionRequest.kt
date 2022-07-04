package com.example.demo.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class TransactionRequest(
    val datetime: String,
    val amount : Double
)