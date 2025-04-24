package no.ntnu.gr04.backend.dto.auth

import java.util.*


data class AuthRegisterRequest(
    val phone: String,

    val password: String,

    val firstName: String,

    val lastName: String,

    val dateOfBirth: Date? = null,
)

data class AuthRegisterResponse(
    val token: String,

    val firstName: String,

    val lastName: String,

    val role: String
)