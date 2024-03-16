package github.crazybolillo.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases(config: ApplicationConfig) {
    val database =
        Database.connect(
            HikariDataSource(
                HikariConfig().apply {
                    driverClassName = config.property("storage.driver").getString()
                    jdbcUrl = config.property("storage.url").getString()
                    username = config.property("storage.user").getString()
                    password = config.property("storage.password").getString()
                    maximumPoolSize = 6
                    isAutoCommit = false
                    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                    validate()
                }
            )
        )
}
