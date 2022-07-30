package local.home.cateat.authentication.adapters.repository

import local.home.cateat.authentication.adapters.repository.dao.UserDao
import local.home.cateat.authentication.adapters.repository.queries.UserGetQuery
import local.home.cateat.authentication.adapters.repository.queries.UserInsertQuery
import local.home.cateat.authentication.core.entities.UserAccount
import local.home.cateat.authentication.core.ports.repository.UserRepository
import local.home.cateat.common.ports.repository.DatabaseRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Repository

@Slf4j
@Repository
class UserRepositoryImpl(private val databaseRepository: DatabaseRepository) : UserRepository {
    override fun createUser(user: UserAccount): Int {
        val query = UserInsertQuery(user)
        return databaseRepository.queryForObject(query) as Int
    }

    override fun getUserByUsername(login: String): UserAccount {
        val query = UserGetQuery(login)
        val userDao = query.getObject(databaseRepository.queryForMap(query)) as UserDao
        return userDao.toUserAccount()
    }
}