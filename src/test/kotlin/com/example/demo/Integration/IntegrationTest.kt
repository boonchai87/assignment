package com.example.demo.Integration

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert hello page, content and status code`() {
        println(">> Assert blog page title, content and status code")
        val entity = restTemplate.getForEntity("/hello",String::class.java)
        //restTemplate.
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("hello world")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}