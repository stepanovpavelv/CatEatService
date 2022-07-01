package local.home.cateat.indication.adapters.repository

import local.home.cateat.indication.adapters.repository.dao.IndicationDao
import local.home.cateat.indication.adapters.repository.queries.IndicationGetByDatesQuery
import local.home.cateat.indication.adapters.repository.queries.IndicationGetQuery
import local.home.cateat.indication.adapters.repository.queries.IndicationInsertQuery
import local.home.cateat.indication.adapters.repository.queries.IndicationRemoveQuery
import local.home.cateat.indication.adapters.repository.queries.enums.IndicationFields
import local.home.cateat.indication.core.entities.Indication
import local.home.cateat.common.ports.repository.DatabaseRepository
import local.home.cateat.indication.core.ports.repository.IndicationRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class IndicationRepositoryImpl(private val databaseRepository: DatabaseRepository) : IndicationRepository {
    override fun createEntry(entry: Indication): Long {
        val query = IndicationInsertQuery(entry)
        return databaseRepository.queryForObject(query) as Long
    }

    override fun getEntries(dateStart: Instant, dateEnd: Instant): List<Indication> {
        val query = IndicationGetByDatesQuery(dateStart, dateEnd)
        return databaseRepository.queryForList(query).map { (query.getObject(it) as IndicationDao).toIndication() }
    }

    override fun getEntryByKey(key: Long): Indication {
        val parameters = mapOf<String, Any>(IndicationFields.ID.field to key)
        val query = IndicationGetQuery(parameters)
        val inDao = query.getObject(databaseRepository.queryForMap(query)) as IndicationDao
        return inDao.toIndication()
    }

    override fun forceDelete(key: Long): Long {
        val query = IndicationRemoveQuery(key)
        return databaseRepository.queryForObject(query) as Long
    }
}