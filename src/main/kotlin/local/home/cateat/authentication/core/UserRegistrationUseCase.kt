package local.home.cateat.authentication.core

import local.home.cateat.authentication.adapters.api.dto.UserRegistrationDto
import local.home.cateat.authentication.core.entities.UserAccount
import local.home.cateat.authentication.core.exception.UserAlreadyExistsException
import local.home.cateat.authentication.core.ports.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Slf4j
@Service
class UserRegistrationUseCase(
    private val userRepository: UserRepository,
    private val userGetUseCase: UserGetUseCase,
    private val passwordEncoder: PasswordEncoder
) {

    /**
     * Регистрация нового пользователя.
     */
    fun saveNewUser(userInfo: UserRegistrationDto) {
        val existingAccount = userGetUseCase.findUserByLogin(userInfo.login)
        if (existingAccount != null) {
            throw UserAlreadyExistsException("Пользователь с логином ${userInfo.login} уже существует.")
        }

        val encodedPassword = passwordEncoder.encode(userInfo.password)
        val userAccount = UserAccount(userInfo.login, encodedPassword, userInfo.enabled)
        userRepository.createUser(userAccount)
    }
}