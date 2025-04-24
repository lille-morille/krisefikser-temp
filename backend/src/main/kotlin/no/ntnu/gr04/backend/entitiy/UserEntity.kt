package no.ntnu.gr04.backend.entitiy

import no.ntnu.gr04.backend.model.User
import java.sql.Date

data class UserEntity(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val passwordHash: String,
    val role: String,
    val positionSharing: Boolean,
    val dateOfBirth: Date? = null,
    )