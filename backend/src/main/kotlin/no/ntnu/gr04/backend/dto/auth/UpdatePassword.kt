package no.ntnu.gr04.backend.dto.auth

data class AuthUpdatePasswordRequest(
    val oldPassword: String,

    val newPassword: String,
)