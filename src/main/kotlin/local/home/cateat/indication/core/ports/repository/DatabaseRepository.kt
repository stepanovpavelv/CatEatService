package local.home.cateat.indication.core.ports.repository

import local.home.cateat.common.Query

/**
 * Основной интерфейс поведения репозитория.
 */
interface DatabaseRepository {
    fun update(query: Query): Int

    fun queryForMap(query: Query): Map<String, Any>

    fun queryForObject(query: Query): Any?

    fun queryForList(query: Query): List<Map<String, Any>>
}