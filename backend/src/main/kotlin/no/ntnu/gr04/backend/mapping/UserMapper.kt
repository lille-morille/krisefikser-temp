package no.ntnu.gr04.backend.mapping

import no.ntnu.gr04.backend.entitiy.UserEntity
import no.ntnu.gr04.backend.model.User
import java.util.*

fun toUser(userEntity: UserEntity): User {
    return User(
        id = UUID.fromString(userEntity.id),
        email = userEntity.email,
        firstName = userEntity.firstName,
        lastName = userEntity.lastName,
        role = User.Role.from(userEntity.role),
        positionSharing = userEntity.positionSharing,
        dateOfBirth = userEntity.dateOfBirth,
    )
}