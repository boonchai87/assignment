package com.example.demo.dto


import org.springframework.web.bind.annotation.RequestBody
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class TransactionRequest(
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val datetime: String,

    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val amount : Double
)