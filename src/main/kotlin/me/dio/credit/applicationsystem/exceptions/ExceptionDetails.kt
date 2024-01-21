package me.dio.credit.applicationsystem.exceptions

import java.sql.Timestamp
import java.time.LocalDateTime

data class ExceptionDetails (
    val tittle: String,
    val timestamp: LocalDateTime,
    val status : Int,
    val exception : String,
    val details: MutableMap<String, String?>
){


}