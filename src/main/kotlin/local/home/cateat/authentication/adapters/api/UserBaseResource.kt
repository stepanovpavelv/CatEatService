package local.home.cateat.authentication.adapters.api

import local.home.cateat.authentication.adapters.api.dto.UserLoginDto
import local.home.cateat.authentication.core.security.TokenManager
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
abstract class UserBaseResource {
    @Autowired
    private lateinit var userDetailsService: UserDetailsService
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var jwtTokenManager: TokenManager

    protected fun createUserToken(userEntry: UserLoginDto): String {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(userEntry.login, userEntry.password)
            )

        } catch (e: DisabledException) {
            throw Exception("User ${userEntry.login} is disabled.", e)
        } catch (e: BadCredentialsException) {
            throw Exception("Invalid credentials received: $userEntry", e)
        }

        val userDetails = userDetailsService.loadUserByUsername(userEntry.login)
        return jwtTokenManager.generateJwtToken(userDetails)
    }
}