package com.jomilanez.apigateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@EnableCircuitBreaker
@SpringBootApplication
@EnableSwagger2WebFlux
class ApiGatewayApplication

fun main(args: Array<String>) {
    runApplication<ApiGatewayApplication>(*args)
}

@RestController
@RequestMapping("/hystrixfallback")
class FallbackController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getDefaultContent(
    ) = "Response from fallback"
}
