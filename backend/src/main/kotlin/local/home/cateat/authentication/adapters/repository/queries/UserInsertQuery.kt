package local.home.cateat.authentication.adapters.repository.queries

import local.home.cateat.authentication.adapters.repository.queries.enums.UserFields
import local.home.cateat.authentication.adapters.repository.queries.enums.UserTable
import local.home.cateat.authentication.core.entities.UserAccount
import local.home.cateat.common.Query

/**
 * Запрос сохранения нового пользователя.
 */
class UserInsertQuery(private val account: UserAccount) : Query {
    override fun getTemplate(): String {
        return """
            INSERT INTO ${UserTable.USER.tableName} (
                ${this.prepareQueryFields("")}
             ) VALUES (
                ${this.prepareQueryFields(":")}             
            ) RETURNING ${UserFields.ID.field}
        """.trimIndent().replace("\n", "")
    }

    override fun getParameters(): Map<String, Any?> {
        return mapOf(UserFields.LOGIN.field to account.login,
            UserFields.PASSWORD.field to account.password)
    }

    private fun prepareQueryFields(prefix: String): String {
        return """
                $prefix${UserFields.LOGIN.field}, 
                $prefix${UserFields.PASSWORD.field} 
        """.trimIndent()
    }
}