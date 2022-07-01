package local.home.cateat.indication.adapters.repository

import local.home.cateat.common.Query
import local.home.cateat.common.ports.repository.DatabaseRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Slf4j
@Repository
class DatabaseRepositoryImpl
constructor(@Qualifier("postgresJdbcTemplate")
            private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : DatabaseRepository {

    override fun update(query: Query): Int {
        return namedParameterJdbcTemplate.update(query.getTemplate(), query.getParameters())
    }

    override fun queryForMap(query: Query): Map<String, Any> {
        return namedParameterJdbcTemplate.queryForMap(query.getTemplate(), query.getParameters())
    }

    override fun queryForObject(query: Query): Any? {
        return namedParameterJdbcTemplate.queryForObject(query.getTemplate(), query.getParameters(), query.getObjectClass())
    }

    override fun queryForList(query: Query): List<Map<String, Any>> {
        return namedParameterJdbcTemplate.queryForList(query.getTemplate(), query.getParameters())
    }
}