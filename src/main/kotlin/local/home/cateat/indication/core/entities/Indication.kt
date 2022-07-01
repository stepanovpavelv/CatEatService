package local.home.cateat.indication.core.entities

import local.home.cateat.indication.adapters.api.dto.IndicationCreateDto
import java.time.Instant

/**
 * Модель события.
 */
class Indication(val createdDate: Instant, val userId: Int, val value: Int) {
    var id: Long? = null

    constructor(id: Long, createdDate: Instant, userId: Int, value: Int) : this(createdDate, userId, value) {
        this.id = id
    }

    companion object {

        fun of(userId: Int, indication: IndicationCreateDto) : Indication {
            return Indication(indication.date, userId, indication.value)
        }
    }
}