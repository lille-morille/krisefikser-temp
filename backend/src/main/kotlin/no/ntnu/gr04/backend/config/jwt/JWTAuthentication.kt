package no.ntnu.gr04.backend.config.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class JWTAuthentication (
    userId: String,
): UsernamePasswordAuthenticationToken(
    userId,
    null
)