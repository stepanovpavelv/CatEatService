package local.home.cateat.authentication.adapters.repository.queries

import local.home.cateat.authentication.adapters.repository.dao.UserDao
import local.home.cateat.authentication.adapters.repository.queries.enums.UserFields
import local.home.cateat.authentication.adapters.repository.queries.enums.UserTable
import local.home.cateat.common.Query

/**
 * Запрос получения пользователя.
 */
class UserGetQuery(private val login: String) : Query {
    override fun getTemplate(): String {
        return """SELECT t1.${UserFields.ID.field},
                         t1.${UserFields.LOGIN.field},
                         t1.${UserFields.PASSWORD.field},
                         t1.${UserFields.ENABLED.field}
                      FROM ${UserTable.USER.tableName} t1 
                      WHERE t1.${UserFields.LOGIN.field} = :${UserFields.LOGIN.field}"""
    }

    override fun getParameters(): Map<String, Any?> {
        return mapOf(UserFields.LOGIN.field to login)
    }

    override fun getObject(queryForMap: Map<String, Any>): Any {
        return UserDao(
            key = queryForMap[UserFields.ID.field].toString().toInt(),
            login = queryForMap[UserFields.LOGIN.field].toString(),
            password = queryForMap[UserFields.PASSWORD.field].toString(),
            enabled = queryForMap[UserFields.ENABLED.field].toString().toBoolean()
        )
    }
}