package local.home.cateat.common

/**
 * Интерфейс запроса.
 */
interface Query {
    fun getTemplate(): String

    fun getParameters(): Map<String, Any?>

    fun getObjectClass(): Class<Any> {
        return Any::class.java
    }

    fun getObject(queryForMap: Map<String, Any>): Any? {
        return null
    }
}