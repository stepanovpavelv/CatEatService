package local.home.cateat.authentication.core.entities

import local.home.cateat.authentication.adapters.api.dto.UserRegistrationDto

/**
 * Модель пользователя.
 */
data class UserAccount(
    val login: String,
    val password: String,
    val enabled: Boolean) {
    var id: Int? = null

    constructor(id: Int, login: String, password: String, enabled: Boolean) : this(login, password, enabled) {
        this.id = id
    }

    companion object {

        fun of(userInfo: UserRegistrationDto) : UserAccount {
            return UserAccount(userInfo.login, userInfo.password, userInfo.enabled)
        }
    }
}