package local.home.cateat.indication.core

import local.home.cateat.indication.core.entities.Indication
import local.home.cateat.indication.core.ports.repository.IndicationRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class IndicationGetUseCase(private val indicationRepository: IndicationRepository) {

    /**
     * Получение записи по ключу.
     */
    fun findByKey(key: Long) : Indication {
        return indicationRepository.getEntryByKey(key)
    }

    /**
     * Получение записей между датами.
     */
    fun findByDates(startDate: Instant, finishDate: Instant) : List<Indication> {
        return indicationRepository.getEntries(startDate, finishDate)
    }
}