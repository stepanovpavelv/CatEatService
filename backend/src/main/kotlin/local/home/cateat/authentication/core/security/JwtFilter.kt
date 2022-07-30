package local.home.cateat.authentication.core.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userDetailsUseCase: JwtUserDetailsUseCase

    @Autowired
    private lateinit var tokenManager: TokenManager

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenHeader = request.getHeader("Authorization")
        var username: String? = null
        var token: String? = null
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7)
            try {
                username = tokenManager.getUsernameFromToken(token)
            } catch (e: IllegalArgumentException) {
                println("Unable to get Jwt Token")
            } catch (e: ExpiredJwtException) {
                println("Jwt Token has expired")
            }
        } else {
            println("Bearer String not found in token")
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsUseCase.loadUserByUsername(username)
            if (tokenManager.validateJwtToken(token, userDetails)) {
                val authenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails.authorities
                )
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}