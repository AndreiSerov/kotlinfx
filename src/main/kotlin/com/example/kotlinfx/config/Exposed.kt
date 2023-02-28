package com.example.kotlinfx.config

import com.example.kotlinfx.persist.ContactTable
import com.zaxxer.hikari.HikariDataSource
import mu.KLogging
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * @author andreiserov
 */
@Configuration
@EnableTransactionManagement
class Exposed {

    @Bean
    fun transactionManager(dataSource: HikariDataSource): SpringTransactionManager {
        logger.info { "=== USE SQL datasource ${dataSource.toDetailsText()}" }
        val transactionManager = SpringTransactionManager(dataSource)

        transaction { SchemaUtils.create(ContactTable) }

        return transactionManager
    }

    @Bean // PersistenceExceptionTranslationPostProcessor with proxyTargetClass=false, see https://github.com/spring-projects/spring-boot/issues/1844
    fun persistenceExceptionTranslationPostProcessor() = PersistenceExceptionTranslationPostProcessor()

    companion object : KLogging()
}

private fun HikariDataSource.toDetailsText(): String =
    "user: $username url: $jdbcUrl pool: $poolName maxPoolSize: $maximumPoolSize minIdle: $minimumIdle"