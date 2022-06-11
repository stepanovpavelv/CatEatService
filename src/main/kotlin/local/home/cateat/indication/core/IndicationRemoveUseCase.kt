package local.home.cateat.indication.core

import local.home.cateat.indication.core.ports.repository.IndicationRepository
import org.springframework.stereotype.Service

@Service
class IndicationRemoveUseCase(private val indicationRepository: IndicationRepository) {

    /**
     * Удаление записи показателя.
     */
    fun deleteEntryByKey(key: Long) : Long {
        return indicationRepository.forceDelete(key)
    }
}