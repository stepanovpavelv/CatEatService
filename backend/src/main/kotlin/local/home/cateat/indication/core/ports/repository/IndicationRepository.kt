package local.home.cateat.indication.core.ports.repository

import local.home.cateat.indication.core.entities.Indication
import java.time.Instant

/**
 * Интерфейс поведения основного репозитория.
 */
interface IndicationRepository {
    fun createEntry(entry: Indication): Long
    fun getEntries(dateStart: Instant, dateEnd: Instant): List<Indication>
    fun getEntryByKey(key: Long): Indication
    fun forceDelete(key: Long): Long
}