package com.example.demo.controller

import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.io.File
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletResponse


@Controller
class HomeController {
    @GetMapping("/")
    fun index(): String {
        //println(this.javaClass.getResourceAsStream("/templates/index.html"))
        //println(this.javaClass.getResource("/templates/index.html").getPath())
        return "index"
    }

    @GetMapping("/download")
    fun downloadFile(response:HttpServletResponse ) {
        response.contentType = "application/octet-stream"
        val headerKey = "Content-Disposition"
        val headerValue = "attachment; filename = collection.json"
        response.setHeader(headerKey, headerValue)
        val outputStream: ServletOutputStream = response.outputStream
        val file = File(this.javaClass.getResource("/templates/assigement.postman_collection.json").getPath()).readBytes()
        outputStream.write(file)
        outputStream.close()
    }
}