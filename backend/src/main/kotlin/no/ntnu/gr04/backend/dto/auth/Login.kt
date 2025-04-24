package no.ntnu.gr04.backend.dto.auth

data class AuthLoginRequest(
    val email: String,
    val password: String,
)

data class AuthLoginResponse(
    val token: String,
    val firstName: String,
    val lastName: String,
    val role: String
)