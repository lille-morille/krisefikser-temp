package no.ntnu.gr04.backend.service

import no.ntnu.gr04.backend.repository.AuthRepository
import no.ntnu.gr04.backend.config.jwt.JWTSigning
import no.ntnu.gr04.backend.dto.auth.AuthLoginRequest
import no.ntnu.gr04.backend.dto.auth.AuthRegisterRequest
import no.ntnu.gr04.backend.mapping.toUser
import no.ntnu.gr04.backend.model.AuthenticatedUser
import no.ntnu.gr04.backend.model.User
import org.apache.logging.log4j.LogManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService (
    private val authRepo: AuthRepository,
    private val jwtSigning: JWTSigning
){
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    private val logger = LogManager.getLogger()

    fun register(request: AuthRegisterRequest): AuthenticatedUser? {
        if (authRepo.find(request.email) == null) {
            return null
        }

        val id = UUID.randomUUID()
        val newUser = User(
            id = id,
            email = request.email,
            firstName = request.firstName,
            lastName = request.lastName,
            dateOfBirth = request.dateOfBirth,
            positionSharing = request.positionSharing
        )

        if (!authRepo.register(newUser, passwordEncoder.encode(request.password))) {
            logger.warn("User ${newUser.id} could not be registered.")
            return null
        }

        return AuthenticatedUser(
            newUser,
            jwtSigning.generateToken(id)
        )
    }

    fun login(request: AuthLoginRequest): AuthenticatedUser? {
        val userEntity = authRepo.find(request.email)!!
        return if (passwordEncoder.matches(request.password, userEntity.passwordHash)) {
            val user = toUser(userEntity);
            AuthenticatedUser(
                user,
                jwtSigning.generateToken(user.id)
            )
        } else {
            null
        }
    }
}