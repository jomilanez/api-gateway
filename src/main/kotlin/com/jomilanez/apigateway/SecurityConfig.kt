package com.jomilanez.apigateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
class SecurityConfig {

    @Bean
    fun springWebFilterChain(
        http: ServerHttpSecurity,
        apiAuthenticationManager: ApiAuthenticationManager,
        securityContextRepository: SecurityContextRepository
    ): SecurityWebFilterChain {
        http
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS).permitAll()
            .pathMatchers(
                "/swagger-ui.html",
                "/v2/api-docs/**",
                "/webjars/**",
                "/swagger-resources/**",
                "**/springfox-swagger-ui/**",
                "/csrf"
            )
            .permitAll()
            .anyExchange()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .authenticationManager(apiAuthenticationManager)
            .securityContextRepository(securityContextRepository)

        return http.build()
    }

}
