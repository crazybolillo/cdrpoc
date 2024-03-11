@file:UseSerializers(InstantSerializer::class)

package github.crazybolillo.models

import github.crazybolillo.serializers.InstantSerializer
import java.time.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.timestamp

object CallDetailTable : IntIdTable("cdr") {
    val from: Column<String> = varchar("src", 256)
    val to: Column<String> = varchar("dst", 256)
    val startedAt: Column<Instant> = timestamp("call_start")
    val answeredAt: Column<Instant?> = timestamp("answer").nullable()
    val endedAt: Column<Instant> = timestamp("call_end")
    val duration: Column<Int> = integer("duration")
    val billed: Column<Int> = integer("billsec")
}

class CallDetail(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CallDetail>(CallDetailTable)

    val from by CallDetailTable.from
    val to by CallDetailTable.to
    val startedAt by CallDetailTable.startedAt
    val answeredAt by CallDetailTable.answeredAt
    val endedAt by CallDetailTable.endedAt
    val duration by CallDetailTable.duration
    val billed by CallDetailTable.billed

    fun toRecord() = CallDetailRecord(id.value, from, to, startedAt, answeredAt, endedAt, duration, billed)
}

@Serializable
data class CallDetailRecord(
    val id: Int,
    val from: String,
    val to: String,
    val startedAt: Instant,
    val answeredAt: Instant?,
    val endedAt: Instant,
    val duration: Int,
    val billed: Int
)
