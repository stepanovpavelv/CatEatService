package local.home.cateat.indication.adapters.repository.queries.enums

/**
 * Перечень полей основной таблицы.
 */
enum class IndicationFields(val field: String) {
    ID("id"),
    CREATE_DATE("create_date"),
    USER_ID("user_id"),
    VALUE("value")
}