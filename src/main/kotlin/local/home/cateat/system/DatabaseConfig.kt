package local.home.cateat.system

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import local.home.cateat.common.util.OsUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.io.FileInputStream
import java.util.Properties
import javax.sql.DataSource

@Configuration
@EnableCaching
class DatabaseConfig {
    @Value("\${app.db.linux-path}")
    val dbLinuxPath: String? = null

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    fun postgresSqlDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    fun postgresSQLDataSource(): DataSource {
        return if (OsUtils.isWindowsOs())
            postgresSqlDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
        else
            manualHikariDataSource()
    }

    @Bean
    fun postgresJdbcTemplate(@Qualifier("postgresSQLDataSource") dataSource: DataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    private fun manualHikariDataSource(): DataSource {
        val config = HikariConfig()

        dbLinuxPath?.let {
            val prop = Properties()
            prop.load(FileInputStream(it))
            config.jdbcUrl = prop.getProperty("host").toString()
            config.driverClassName = prop.getProperty("driver").toString()
            config.username = prop.getProperty("username").toString()
            config.password = prop.getProperty("password").toString()
        }

        return HikariDataSource(config)
    }
}