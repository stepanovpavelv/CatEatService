package local.home.cateat.indication.adapters.repository.queries

import local.home.cateat.common.Query
import local.home.cateat.common.util.DateTimeUtils
import local.home.cateat.indication.adapters.repository.dao.IndicationDao
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationFields
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationTable
import java.time.Instant

/**
 * Запрос на получение показателей по датам.
 */
class IndicationGetByDatesQuery(private val startDate: Instant, private val endDate: Instant) : Query {
    override fun getTemplate(): String {
        return """SELECT t1.${IndicationFields.ID.field},
                         t1.${IndicationFields.CREATE_DATE.field},
                         t1.${IndicationFields.USER_ID.field},
                         t1.${IndicationFields.VALUE.field}
                   FROM ${IndicationTable.INDICATION.tableName} t1
                   WHERE t1.${IndicationFields.CREATE_DATE.field} between :startDate and :endDate """
    }

    override fun getParameters(): Map<String, Any?> {
        return mapOf(
            Pair("startDate", DateTimeUtils.fromInstant(startDate)),
            Pair("endDate", DateTimeUtils.fromInstant(endDate))
        )
    }

    override fun getObject(queryForMap: Map<String, Any>): Any {
        return IndicationDao(
            key = queryForMap[IndicationFields.ID.field].toString().toLong(),
            date = DateTimeUtils.toInstant(queryForMap[IndicationFields.CREATE_DATE.field].toString()),
            userId = queryForMap[IndicationFields.USER_ID.field].toString().toInt(),
            value = queryForMap[IndicationFields.VALUE.field].toString().toInt()
        )
    }
}