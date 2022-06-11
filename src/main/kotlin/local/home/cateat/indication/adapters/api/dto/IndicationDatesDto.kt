package local.home.cateat.indication.adapters.api.dto

import java.time.Instant

/**
 * Dto-объект получения сущности по датам события.
 */
class IndicationDatesDto(val startDate: Instant, val finishDate: Instant)