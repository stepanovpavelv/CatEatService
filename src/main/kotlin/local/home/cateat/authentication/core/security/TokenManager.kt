package local.home.cateat.authentication.core.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenManager {
    @Value("\${jwt-secret}")
    private lateinit var jwtSecret: String

    fun generateJwtToken(userDetails: UserDetails): String {
        val claims = mapOf<String, Any>()
        return Jwts.builder().setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun validateJwtToken(token: String?, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        val claims: Claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        val isTokenExpired = claims.expiration.before(Date())
        return username == userDetails.username && !isTokenExpired
    }

    fun getUsernameFromToken(token: String?): String {
        val claims: Claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.subject
    }

    fun getCurrentUsername() : String {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.name
    }

    companion object {
        private const val TOKEN_VALIDITY = 10 * 60 * 60 // 10 минут
    }
}