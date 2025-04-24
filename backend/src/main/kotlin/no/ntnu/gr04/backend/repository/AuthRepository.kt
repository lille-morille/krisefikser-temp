package no.ntnu.gr04.backend.repository

import no.ntnu.gr04.backend.entitiy.UserEntity
import no.ntnu.gr04.backend.model.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AuthRepository(private val jdbc: JdbcTemplate) {

    private val rowMapper = RowMapper { rs, _ ->
        UserEntity(
            id = rs.getString("id"),
            passwordHash = rs.getString("password_hash"),
            firstName = rs.getString("first_name"),
            lastName = rs.getString("last_name"),
            role = rs.getString("role"),
            email = rs.getString("email"),
            positionSharing = rs.getBoolean("position_sharing"),
            dateOfBirth = rs.getDate("date_of_birth"),
        )
    }

    fun find(email: String): UserEntity? {
        val sql = "SELECT * FROM users WHERE email = ?"
        return try {
            jdbc.queryForObject(sql, rowMapper, email)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun register(user: User, passwordHash: String): Boolean {
        val sql = """
            INSERT INTO users (id, email, firstName, lastName, role, positionSharing,
            dateOfBirth, passwordHash) VALUES (?,?,?,?,?,?,?,?)
        """
        return jdbc.update(
            sql, user.id, user.email, user.firstName, user.lastName, user.role, user.positionSharing,
            user.dateOfBirth, passwordHash
        ) != 0
    }
}