package local.home.cateat.indication.adapters.repository.queries

import local.home.cateat.common.Query
import local.home.cateat.common.util.DateTimeUtils
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationFields
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationTable
import local.home.cateat.indication.core.entities.Indication

/**
 * Запрос на вставку показателя.
 */
class IndicationInsertQuery(private val indication: Indication) : Query {

    override fun getTemplate(): String {
        return """
            INSERT INTO ${IndicationTable.INDICATION.tableName} (
                ${this.prepareQueryFields("")}
             ) VALUES (
                ${this.prepareQueryFields(":")}             
            ) RETURNING ${IndicationFields.ID.field}
        """.trimIndent().replace("\n", "")
    }

    override fun getParameters(): Map<String, Any?> {
        return mapOf(IndicationFields.CREATE_DATE.field to DateTimeUtils.fromInstant(indication.createdDate),
                     IndicationFields.VALUE.field to indication.value)
    }

    private fun prepareQueryFields(prefix: String): String {
        return """
                $prefix${IndicationFields.CREATE_DATE.field}, 
                $prefix${IndicationFields.VALUE.field} 
        """.trimIndent()
    }
}