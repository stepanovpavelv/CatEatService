package local.home.cateat.indication.adapters.api.dto

import local.home.cateat.indication.core.entities.Indication
import java.time.Instant

/**
 * Dto-объект показателя.
 */
class IndicationDto(
    val id: Long?,
    val date: Instant,
    val value: Int
) {
    constructor(indication: Indication) : this(indication.id, indication.createdDate, indication.value)

    companion object {

        fun listOf(indications: List<Indication>) : List<IndicationDto> {
            return indications.map { IndicationDto(it) }
        }
    }
}