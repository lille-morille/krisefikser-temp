package no.ntnu.gr04.backend.controller

import no.ntnu.gr04.backend.dto.auth.AuthLoginRequest
import no.ntnu.gr04.backend.dto.auth.AuthLoginResponse
import no.ntnu.gr04.backend.dto.auth.AuthRegisterRequest
import no.ntnu.gr04.backend.dto.auth.AuthRegisterResponse
import no.ntnu.gr04.backend.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: AuthRegisterRequest): ResponseEntity<AuthRegisterResponse> {
        val authenticatedUser = authService.register(request) ?: return ResponseEntity(HttpStatus.CONFLICT)
        val body = AuthRegisterResponse(
            token = authenticatedUser.jwt,
            firstName = authenticatedUser.user.firstName,
            lastName = authenticatedUser.user.lastName,
            role = authenticatedUser.user.role.toString()
        )
        return ResponseEntity.ok(body)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthLoginRequest): ResponseEntity<AuthLoginResponse> {
        val authenticatedUser = authService.login(request) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val body = AuthLoginResponse(
            token = authenticatedUser.jwt,
            firstName = authenticatedUser.user.firstName,
            lastName = authenticatedUser.user.lastName,
            role = authenticatedUser.user.role.toString()
        )
        return ResponseEntity.ok(body)
    }
}