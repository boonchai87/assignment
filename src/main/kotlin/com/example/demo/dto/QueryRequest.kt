package com.example.demo.dto


import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class QueryRequest (
        @NotNull
        @NotBlank
        val startDatetime: String,

        @NotNull
        @NotBlank
        val endDatetime : String
        )
