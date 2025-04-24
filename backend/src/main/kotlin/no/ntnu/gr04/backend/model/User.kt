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
    enum class Role (private val value: String) {
        ADMIN("admin"),
        USER("user"),;

        override fun toString(): String {
            return value
        }

        companion object {
            fun from(string: String): Role {
                return Role.entries.find { it.value == string } ?: throw IllegalArgumentException("Unknown role.")
            }
        }
    }
}
