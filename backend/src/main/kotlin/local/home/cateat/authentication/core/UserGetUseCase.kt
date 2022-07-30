package local.home.cateat.authentication.core

import local.home.cateat.authentication.core.entities.UserAccount
import local.home.cateat.authentication.core.ports.repository.UserRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Slf4j
@Service
class UserGetUseCase(private val userRepository: UserRepository) {

    /**
     * Получение пользователя по логину.
     */
    fun findUserByLogin(userName: String) : UserAccount? {
        return try {
            userRepository.getUserByUsername(userName)
        } catch (ex: EmptyResultDataAccessException) {
            log.info("Пользователь $userName не найден в БД!")
            null
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}