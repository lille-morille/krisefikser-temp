package no.ntnu.gr04.backend.model

data class AuthenticatedUser(
    val user: User,
    val jwt: String
)