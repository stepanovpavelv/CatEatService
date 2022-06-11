package local.home.cateat.indication.adapters.api.dto

import java.time.Instant

/**
 * Dto-объект создания показателя.
 */
class IndicationCreateDto(
    val date: Instant,
    val value: Int
)