package com.example.braindump.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

@Entity(tableName = "Dumps")
data class Dump(
    val feelings : Int,
    val dump: String,
    @PrimaryKey
    val date: Long,
):Serializable

fun Dump.formattedDate(): String{
    val format = "EEEE, h:mm a"
    val time = SimpleDateFormat(format).format(Date(date))
    return time
}
