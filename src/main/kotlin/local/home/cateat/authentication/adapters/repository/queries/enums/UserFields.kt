package local.home.cateat.authentication.adapters.repository.queries.enums

/**
 * Поля таблицы пользователей.
 */
enum class UserFields(val field: String) {
    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    ENABLED("enabled");
}