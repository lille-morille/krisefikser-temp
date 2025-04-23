package no.ntnu.gr04.backend.config.jwt

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

    fun validate(token: String): JWTAuthentication {
        val claims = parser.parseClaimsJws(token)!!.body !!
        return JWTAuthentication(claims.subject !!)
    }
}