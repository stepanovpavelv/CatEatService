package local.home.cateat.authentication.adapters.api.dto

/**
 * dto-класс регистрации пользователя в системе.
 */
class UserRegistrationDto(val login: String, val password: String, val enabled: Boolean)