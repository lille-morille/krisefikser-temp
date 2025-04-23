package no.ntnu.gr04.backend.config.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

private const val AUTH_HEADER: String = "Authorization"
private const val BEARER: String = "Bearer "

@Component
class JWTFilter(
    private val jwtSigner: JWTSigning
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val bearerToken = request.getHeader(AUTH_HEADER)

        if (bearerToken == null) {
            filterChain.doFilter(request, response)
            return
        }

        val token = if (bearerToken.startsWith(BEARER)) {
            bearerToken.substring(BEARER.length)
        } else {
            bearerToken
        }

        val auth = jwtSigner.validate(token)

        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(request, response)
    }
}
