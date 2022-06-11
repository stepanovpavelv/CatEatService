package local.home.cateat.indication.core.entities

import local.home.cateat.indication.adapters.api.dto.IndicationCreateDto
import java.time.Instant
import java.util.Date

/**
 * Модель события.
 */
class Indication(val createdDate: Instant, val value: Int) {
    var id: Long? = null

    constructor(id: Long, createdDate: Instant, value: Int) : this(createdDate, value) {
        this.id = id
    }

    companion object {

        fun of(indication: IndicationCreateDto) : Indication {
            return Indication(indication.date, indication.value)
        }
    }
}