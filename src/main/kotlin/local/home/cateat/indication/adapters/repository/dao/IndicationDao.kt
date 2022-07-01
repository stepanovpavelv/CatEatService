package local.home.cateat.indication.adapters.repository.dao

import local.home.cateat.indication.core.entities.Indication
import java.time.Instant

/**
 * Модель хранения показателя.
 */
data class IndicationDao(
    val key: Long,
    val date: Instant,
    val userId: Int,
    val value: Int
) {
    fun toIndication() : Indication {
        return Indication(key, date, userId, value)
    }
}