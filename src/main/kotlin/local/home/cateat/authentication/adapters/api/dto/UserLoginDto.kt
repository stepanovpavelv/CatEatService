package local.home.cateat.authentication.adapters.api.dto

/**
 * dto-класс для получения авторизационного токена.
 */
data class UserLoginDto(val login: String, val password: String) {
    companion object {

        fun of(registerInfo: UserRegistrationDto) : UserLoginDto {
            return UserLoginDto(registerInfo.login, registerInfo.password)
        }
    }
}