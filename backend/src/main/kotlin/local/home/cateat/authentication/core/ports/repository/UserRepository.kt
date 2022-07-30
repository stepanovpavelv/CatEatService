package local.home.cateat.authentication.core.ports.repository

import local.home.cateat.authentication.core.entities.UserAccount

/**
 * Интерфейс поведения репозитория пользователей.
 */
interface UserRepository {
    fun createUser(user: UserAccount): Int
    fun getUserByUsername(login: String): UserAccount
}