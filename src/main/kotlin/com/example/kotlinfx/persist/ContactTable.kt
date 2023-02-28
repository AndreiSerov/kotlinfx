package com.example.kotlinfx.persist

import com.example.kotlinfx.domain.Contact
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @author andreiserov
 */
object ContactTable : IntIdTable() {
    val name = varchar("name", 50)
    val phone = varchar("phone", 50)
}

object ContactDao {
    fun createContact(contact: Contact) {
        transaction {
            ContactTable.insert {
                it[name] = contact.name
                it[phone] = contact.phone
            }
        }
    }

    fun findAll() = transaction {
        ContactTable.selectAll().map { ContactTable.toModel(it) }
    }
}

fun ContactTable.toModel(row: ResultRow) = Contact(
    row[name], row[phone]
)