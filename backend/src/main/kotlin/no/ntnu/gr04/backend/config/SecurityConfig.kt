package no.ntnu.gr04.backend.config

import no.ntnu.gr04.backend.config.jwt.JWTFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity, filter: JWTFilter): SecurityFilterChain {
        http
            .cors { }
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/api/public/**",
                        "/v3/api-docs/**",
                        "/api/auth/login",
                        "/api/auth/register",
                        "/swagger-ui/*"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}
