package github.crazybolillo.controllers

import github.crazybolillo.models.CallDetail
import github.crazybolillo.models.CallDetailRecord
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
        val start = call.parameters["_start"]?.toInt() ?: 0
        val end = call.parameters["_end"]?.toInt() ?: 10

        var count: Long = 0
        var records: List<CallDetailRecord> = listOf()
        transaction {
            val query =
                CallDetailTable.selectAll()
                    .orderBy(CallDetailTable.startedAt to SortOrder.DESC)
                    .limit(end - start, offset = end.toLong())

            records = CallDetail.wrapRows(query).toList().map { it.toRecord() }
            count = CallDetailTable.selectAll().count()
        }

        call.response.headers.append(HttpHeaders.XTotalCount, count.toString())
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
