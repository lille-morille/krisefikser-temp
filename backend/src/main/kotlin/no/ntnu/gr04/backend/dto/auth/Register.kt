package no.ntnu.gr04.backend.dto.auth

import java.util.*


data class AuthRegisterRequest(
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Date? = null,
    val positionSharing: Boolean = false
)

data class AuthRegisterResponse(
    val token: String,
    val firstName: String,
    val lastName: String,
    val role: String
)