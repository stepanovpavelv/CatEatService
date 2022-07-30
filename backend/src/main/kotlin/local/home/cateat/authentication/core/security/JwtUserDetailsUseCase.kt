package local.home.cateat.authentication.core.security

import local.home.cateat.authentication.core.UserGetUseCase
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsUseCase(private val userGetUseCase: UserGetUseCase) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val account = userGetUseCase.findUserByLogin(login)
            ?: throw UsernameNotFoundException("Пользователь с логином $login не найден")

        return User(
            account.login,
            account.password,
            account.enabled,
            true,
            true,
            true,
            listOf<GrantedAuthority>())
    }
}