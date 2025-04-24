package no.ntnu.gr04.backend.model

import java.util.*

data class User(
    val id: UUID,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: Role = Role.USER,
    val positionSharing: Boolean = false,
    val dateOfBirth: Date? = null,
    ) {
    enum class Role {
        ADMIN,
        USER,
    }
}
