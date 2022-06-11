package local.home.cateat.indication.adapters.repository.queries

import local.home.cateat.common.Query
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationFields
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationTable

/**
 * Запрос на удаление показателя.
 */
class IndicationRemoveQuery(private val key: Long) : Query {

    override fun getTemplate(): String {
        return """
            DELETE 
            FROM ${IndicationTable.INDICATION.tableName} 
            WHERE ${IndicationFields.ID.field} = :id 
            RETURNING ${IndicationFields.ID.field} 
        """.trimIndent().replace("\n", "")
    }

    override fun getParameters(): Map<String, Any?> {
        return mapOf(IndicationFields.ID.field to key)
    }
}