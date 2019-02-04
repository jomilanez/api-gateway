package com.jomilanez.apigateway

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
class ApiSecurityConfiguration {

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build()
        return MapReactiveUserDetailsService(user)
    }


    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        return http.csrf()
            .disable()
            .authorizeExchange()
            .pathMatchers(
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html**",
                "/webjars/**",
                "/actuator/*"
            ).permitAll()
            .anyExchange().authenticated()
            .and().build()
    }
}
