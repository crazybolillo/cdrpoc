package github.crazybolillo.controllers

import github.crazybolillo.models.CallDetail
import github.crazybolillo.models.CallDetailTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.util.*
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object CallDetailController {
    suspend fun list(call: ApplicationCall) {
        val page = call.parameters["page"]?.toInt() ?: 1
        val size = call.parameters["size"]?.toInt() ?: 20

        val records = transaction {
            val query =
                CallDetailTable.selectAll()
                    .orderBy(CallDetailTable.startedAt to SortOrder.DESC)
                    .limit(size, offset = (size * page).toLong())

            CallDetail.wrapRows(query).toList().map { it.toRecord() }
        }

        call.respond(records)
    }

    suspend fun get(call: ApplicationCall) {
        val id = call.parameters.getOrFail<Int>("id").toInt()
        val record = transaction { CallDetail.findById(id) }
        record?.let {
            call.respond(it.toRecord())
            return
        }

        call.respond(HttpStatusCode.BadRequest)
    }
}
