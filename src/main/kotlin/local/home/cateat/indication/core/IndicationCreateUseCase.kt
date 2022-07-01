package local.home.cateat.indication.core

import local.home.cateat.authentication.core.UserGetUseCase
import local.home.cateat.authentication.core.security.TokenManager
import local.home.cateat.indication.adapters.api.dto.IndicationCreateDto
import local.home.cateat.indication.core.entities.Indication
import local.home.cateat.indication.core.ports.repository.IndicationRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class IndicationCreateUseCase(
    private val tokenManager: TokenManager,
    private val userGetUseCase: UserGetUseCase,
    private val indicationRepository: IndicationRepository) {

    /**
     * Создание записи показателя.
     */
    fun createEntry(indicationDto: IndicationCreateDto) : Long {
        val username = tokenManager.getCurrentUsername()
        val userAccount = userGetUseCase.findUserByLogin(username)
        val indication = userAccount?.id?.let {
            Indication.of(it, indicationDto)
        } ?: throw UsernameNotFoundException("Пользователь $username не найден")

        return indicationRepository.createEntry(indication)
    }
}