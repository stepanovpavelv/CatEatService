package local.home.cateat.indication.core

import local.home.cateat.indication.adapters.api.dto.IndicationCreateDto
import local.home.cateat.indication.core.entities.Indication
import local.home.cateat.indication.core.ports.repository.IndicationRepository
import org.springframework.stereotype.Service

@Service
class IndicationCreateUseCase(private val indicationRepository: IndicationRepository) {

    /**
     * Создание записи показателя.
     */
    fun createEntry(indicationDto: IndicationCreateDto) : Long {
        val indication = Indication.of(indicationDto)
        return indicationRepository.createEntry(indication)
    }
}