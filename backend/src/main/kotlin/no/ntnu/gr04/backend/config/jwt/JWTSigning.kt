package no.ntnu.gr04.backend.config.jwt

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JWTSigning {
    private val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256) !!
    private val parser: JwtParser = Jwts.parserBuilder()
        .setSigningKey(key)
        .build() !!

    fun validate(token: String): JWTAuthentication? {
        val claims = try {
            parser.parseClaimsJws(token)!!.body !!
        } catch (e: JwtException) {
            return null
        }

        return JWTAuthentication(claims.subject !!)
    }
}