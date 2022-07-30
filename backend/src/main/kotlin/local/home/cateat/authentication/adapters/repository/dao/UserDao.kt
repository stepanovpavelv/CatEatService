package local.home.cateat.authentication.adapters.repository.dao

import local.home.cateat.authentication.core.entities.UserAccount

/**
 * Модель хранения пользователя.
 */
data class UserDao(
    val key: Int,
    val login: String,
    val password: String,
    val enabled: Boolean) {

    fun toUserAccount(): UserAccount {
        return UserAccount(key, login, password, enabled)
    }
}