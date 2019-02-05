package com.jomilanez.apigateway

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
class ApiSecurityConfiguration(
    val authenticationManager: ReactiveAuthenticationManager,
    val securityContextRepository: SecurityContextRepository
) {

    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .securityContextRepository(securityContextRepository)
            .authenticationManager(authenticationManager)
            .authorizeExchange()
            .pathMatchers(
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html**",
                "/webjars/**",
                "/actuator/**"
            ).permitAll()
            .anyExchange().authenticated()
            .and().build()
    }
}
