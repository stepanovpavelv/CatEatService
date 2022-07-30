package local.home.cateat.indication.adapters.repository.queries

import local.home.cateat.common.Query
import local.home.cateat.common.util.DateTimeUtils
import local.home.cateat.indication.adapters.repository.dao.IndicationDao
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationFields
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationTable

/**
 * Запрос на получение показателей (в том числе по условиям).
 */
class IndicationGetQuery(private val whereParameters: Map<String, Any> = mapOf()) : Query {
    override fun getTemplate(): String {
        var sql = """SELECT t1.${IndicationFields.ID.field},
                            t1.${IndicationFields.CREATE_DATE.field},
                            t1.${IndicationFields.USER_ID.field},
                            t1.${IndicationFields.VALUE.field}
                      FROM ${IndicationTable.INDICATION.tableName} t1 """

        if (whereParameters.isNotEmpty()) {
            sql += " WHERE ${prepareWhereFields()} "
        }
        return sql
    }

    override fun getParameters(): Map<String, Any?> {
        return whereParameters
    }

    override fun getObject(queryForMap: Map<String, Any>): Any {
        return IndicationDao(
            key = queryForMap[IndicationFields.ID.field].toString().toLong(),
            date = DateTimeUtils.toInstant(queryForMap[IndicationFields.CREATE_DATE.field].toString()),
            userId = queryForMap[IndicationFields.USER_ID.field].toString().toInt(),
            value = queryForMap[IndicationFields.VALUE.field].toString().toInt()
        )
    }

    private fun prepareWhereFields(): String {
        return whereParameters.keys.joinToString(" AND ") {
            "t1.$it = :${it}"
        }
    }
}